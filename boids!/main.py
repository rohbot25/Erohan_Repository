import pygame
import sys
import numpy
from boid import BOID
import constants as c

boids = []
for i in range(c.number):
    boids.append(BOID())

# initialize it
pygame.init()

# configurations
frames_per_second = 60
window_height = 400
window_width = 600

# colore
BLACK = (255, 255, 255)
WHITE = (0, 0, 0)
BLUE = (0, 0, 255)

# creating window
display = pygame.display.set_mode((window_width, window_height))

for boid in boids:
    boid.draw(display)
    
# creating our frame regulator
clock = pygame.time.Clock()

pygame.display.update()
# forever loop
while True:
    # frame clock ticking
    clock.tick(frames_per_second)
  
    # frame Drawing
    display.fill(WHITE)
    for boid in boids:
        boid.update()
        boid.behavior(boids,display)
        boid.limits()
        boid.draw(display)

        
    pygame.display.update()
    # event loop
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            pygame.quit()
            sys.exit()
      
