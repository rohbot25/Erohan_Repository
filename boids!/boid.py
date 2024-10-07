import random
import pygame
import constants as c
import math

class BOID:
    #constructor of the boid object
    def __init__(self):
        #put a boid in a random position
        self.posX = random.randint(50,550)
        self.posY = random.randint(50,350)
        
        #start with a random speed
        self.velX = random.randint(-1 * c.speed,c.speed)
        self.velY = random.randint(-1 * c.speed,c.speed)
        
        #size and color 
        self.size = c.size
        self.color = c.color
    #update position
    def update(self):
        self.posX += self.velX
        self.posY += self.velY
    #limit speed, start forcing boids to turn when they get close to the border
    def limits(self):
        if(self.posX < c.border):
            self.velX += c.turnRadius
        if(self.posX > c.width - c.border):
            self.velX -= c.turnRadius
        if(self.posY < c.border):
            self.velY += c.turnRadius
        if(self.posY > c.height - c.border):
            self.velY -= c.turnRadius

    #update the behavior of the boid
    def behavior(self,neighbors,display):
        #separation
        separation = self.separation(neighbors)
        self.velX += separation[0] * c.separation_factor
        self.velY += separation[1] * c.separation_factor
        #alignment
        alignment = self.alignment(neighbors)
        self.velX += alignment[0] * c.alignment_factor
        self.velY += alignment[1] * c.alignment_factor
        #cohesion
        cohesion = self.cohesion(neighbors,display)
        self.velX += cohesion[0] * c.cohesion_factor
        self.velY += cohesion[1] * c.cohesion_factor
        #adjust speed
        magnitude = math.sqrt(math.pow(self.velX,2) + math.pow(self.velY,2))
        if(magnitude != 0):
            self.velX /= magnitude 
            self.velY /= magnitude
            self.velX *= c.speed
            self.velY *= c.speed 
    #steer the boid towards the direction its neighbors are facing
    def alignment(self,neighbors):
        #steering vector
        steering = [0,0]
        total = 0
        #for all neghbors
        for boid in neighbors:
            #Check if boid is within the vision of the boid
            distance = math.dist([self.posX,self.posY],[boid.posX,boid.posY])
            if(distance <= c.vision and boid is not self):
                #add to total
                total += 1
                #add its direction to the steering affect
                steering[0] += boid.velX
                steering[1] += boid.velY

        #if their were boids in view
        if(total != 0):
            #average the steering factor
            steering = [x / total for x in steering]
            #find the length of the vector -> how strong in that direction
            steerLength = math.sqrt(math.pow(steering[0],2)+math.pow(steering[1],2))
            #if the length is not 0
            if(steerLength !=0):
                #create a unit vector for steering
                steering = [x / steerLength for x in steering]
                #multiply by the max speed chosen for the simulation
                steering = [x * c.speed for x in steering]
                #add effect to boids speed
                steering[0] -= self.velX
                steering[1] -= self.velY
                #if the boid is going faster than the max speed, cut it back to be within that range
                length = math.sqrt(math.pow(steering[0],2)+math.pow(steering[1],2))
                if(length > c.maxForce):
                    steering = [x * (c.maxForce/length) for x in steering]
        return steering

    #try to move the boid closer to the position of its neighbors
    def cohesion(self,neighbors,display):
        #create steering vector, and a direction vector
        steering = [0,0]
        direction = [0,0]
        total = 0
        #for all neighbors
        for boid in neighbors:
            #get the distance the neighbor is away and check if they are in vision
            distance = math.dist([self.posX,self.posY],[boid.posX,boid.posY])
            if (distance <= c.vision and boid is not self): 
                #add where the boid is to steering vector
                total += 1
                steering[0] += boid.posX
                steering[1] += boid.posY
        if(total != 0):
            #find the average position of neighbors
            steering = [x / total for x in steering]
            #find the vector between us and that point
            direction = [steering[0] - self.posX, steering[1] - self.posY]
            #adjust direction based on the speed of the simulation
            directionLength = math.sqrt(math.pow(direction[0],2)+math.pow(direction[1],2))
            direction = [ x / directionLength for x in direction]
            direction = [x * c.speed for x in direction]
            #add to the velocity of our boid
            direction[0] -= self.velX
            direction[1] -= self.velY
            #if faster than simulation, bring it back into what it should be.
            length = math.sqrt(math.pow(direction[0],2)+math.pow(direction[1],2))
            if(length > c.maxForce):
                direction = [x * (c.maxForce/length) for x in direction]
            #pygame.draw.circle(display, (255,0,0),(steering[0],steering[1]),self.size)
        return direction
    #move boid away from its neighbors
    def separation(self,neighbors):
        #make a steering vector
        steering = [0,0]
        
        #for all neighbors
        for boid in neighbors:
            total = 0
            #find the direction from us to our neighbor
            direction = [boid.posX - self.posX , boid.posY - self.posY]
            #check if the neighbor is within our vision
            distance = math.dist([self.posX,self.posY],[boid.posX,boid.posY])
            if (distance <= c.vision and boid is not self):
                total += 1
                #normalize the direction and move in the opposite direction
                direction = [x / distance for x in direction]
                steering[0] -= direction[0] / distance
                steering[1] -= direction[1] / distance
        if(total != 0):
            #average the steering factor
            steering = [x / total for x in steering]

        #normalize the vector depending on the speed of our simulation
        steerLength = math.sqrt(math.pow(steering[0],2)+math.pow(steering[1],2))
        if(not(steering == [0,0])):
            steering = [x / steerLength for x in steering]
            steering = [x * c.speed for x in steering]
            #add to our boid
            steering[0] -= self.velX
            steering[1] -= self.velY
            #if faster than we want, bring it back to the correct speed
            length = math.sqrt(math.pow(steering[0],2)+math.pow(steering[1],2))
            if(length > c.maxForce):
                steering = [x * (c.maxForce/length) for x in steering]
         
        
        return steering    
            
            
    #draw where the boid is      
    def draw(self,display):
        pygame.draw.circle(display, self.color,(self.posX,self.posY),self.size)

    

