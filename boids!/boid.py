import numpy
import random
import pygame
import constants as c
import math

class BOID:
    def __init__(self):
        self.posX = random.randint(50,550)
        self.posY = random.randint(50,350)
        
        self.velX = random.randint(-1 * c.speed,c.speed)
        self.velY = random.randint(-1 * c.speed,c.speed)
        
        self.size = 5
        self.color = (255,0,255)
        
    def update(self):
        self.posX += self.velX
        self.posY += self.velY

    def limits(self):
        if(self.posX < c.border):
            self.velX += c.turnRadius
        if(self.posX > c.width - c.border):
            self.velX -= c.turnRadius
        if(self.posY < c.border):
            self.velY += c.turnRadius
        if(self.posY > c.height - c.border):
            self.velY -= c.turnRadius
        
    def behavior(self,neighbors,display):
        #separation
        separation = self.separation(neighbors)
        self.velX += separation[0] * 2
        self.velY += separation[1] * 2
        #alignment
        alignment = self.alignment(neighbors)
        self.velX += alignment[0] 
        self.velY += alignment[1] 
        #cohesion
        cohesion = self.cohesion(neighbors,display)
        self.velX += cohesion[0] 
        self.velY += cohesion[1] 
        #adjust speed
        magnitude = math.sqrt(math.pow(self.velX,2) + math.pow(self.velY,2))
        if(magnitude != 0):
            self.velX /= magnitude 
            self.velY /= magnitude
            self.velX *= c.speed
            self.velY *= c.speed 
    def alignment(self,neighbors):
        steering = [0,0]
        total = 0
        for boid in neighbors:
            distance = math.dist([self.posX,self.posY],[boid.posX,boid.posY])
            if(distance <= c.vision and boid is not self):
                total += 1
                steering[0] += boid.velX
                steering[1] += boid.velY
        
        if(total != 0):
            steering = [x / total for x in steering]
            steerLength = math.sqrt(math.pow(steering[0],2)+math.pow(steering[1],2))
            if(steerLength !=0):
                steering = [x / steerLength for x in steering]
                steering = [x * c.speed for x in steering]
                steering[0] -= self.velX
                steering[1] -= self.velY
                length = math.sqrt(math.pow(steering[0],2)+math.pow(steering[1],2))
                if(length > c.maxForce):
                    steering = [x * (c.maxForce/length) for x in steering]
        return steering

    def cohesion(self,neighbors,display):
        steering = [0,0]
        direction = [0,0]
        total = 0
        for boid in neighbors:
            distance = math.dist([self.posX,self.posY],[boid.posX,boid.posY])
            if (distance <= c.vision and boid is not self): 
                total += 1
                steering[0] += boid.posX
                steering[1] += boid.posY
        if(total != 0):
            steering = [x / total for x in steering]
            direction = [steering[0] - self.posX, steering[1] - self.posY]
            directionLength = math.sqrt(math.pow(direction[0],2)+math.pow(direction[1],2))
            direction = [ x / directionLength for x in direction]
            direction = [x * c.speed for x in direction]
            direction[0] -= self.velX
            direction[1] -= self.velY
            length = math.sqrt(math.pow(direction[0],2)+math.pow(direction[1],2))
            if(length > c.maxForce):
                direction = [x * (c.maxForce/length) for x in direction]
            #pygame.draw.circle(display, (255,0,0),(steering[0],steering[1]),self.size)
        return direction

    def separation(self,neighbors):
        steering = [0,0]
        
        for boid in neighbors:
            total = 0
            direction = [boid.posX - self.posX , boid.posY - self.posY]
            distance = math.dist([self.posX,self.posY],[boid.posX,boid.posY])
            if (distance <= c.vision and boid is not self):
                total += 1
                #normalize
                direction = [x / distance for x in direction]
                steering[0] -= direction[0] / distance
                steering[1] -= direction[1] / distance
        if(total != 0):
            steering = [x / total for x in steering]
         
        steerLength = math.sqrt(math.pow(steering[0],2)+math.pow(steering[1],2))
        if(not(steering == [0,0])):
            steering = [x / steerLength for x in steering]
            steering = [x * c.speed for x in steering]
            steering[0] -= self.velX
            steering[1] -= self.velY
            length = math.sqrt(math.pow(steering[0],2)+math.pow(steering[1],2))
            if(length > c.maxForce):
                steering = [x * (c.maxForce/length) for x in steering]
         
        
        return steering    
            
            
            
    def draw(self,display):
        pygame.draw.circle(display, self.color,(self.posX,self.posY),self.size)

    

