from flask import Flask, render_template
import threading
import time
import RPi.GPIO as GPIO

def fill():
  #enable gpio pins
  #Pin for sensor
  SENSOR = 18
  #pin for pump
  PUMP = 15
  GPIO.setmode(GPIO.BCM)
  GPIO.setwarnings(False)
  GPIO.setup(SENSOR, GPIO.in)
  GPIO.setup(PUMP, GPIO.output)
  #off
  GPIO.output(PUMP, GPIO.HIGH)
  while (GPIO.input(SENSOR) == 0):
    GPIO.output(PUMP, GPIO.LOW)
    time.sleep(.5)
  GPIO.output(PUMP,GPIO.HIGH)
def check():
  global check
  SENSOR = 18
  PUMP = 15
  GPIO.setmode(GPIO.BCM)
  GPIO.setwarnings(False)
  GPIO.setup(SENSOR, GPIO.in)
  GPIO.setup(PUMP, GPIO.output)
  GPIO.output(PUMP, GPIO.HIGH)
  while check:  
    while (GPIO.input(SENSOR) == 0):
      GPIO.output(PUMP, GPIO.LOW)
      time.sleep(.5)
    GPIO.output(PUMP.GPIO.HIGH)
    time.sleep(30)

GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(False)
GPIO.setup(15, GPIO.output)
GPIO.setup(24,GPIO.output)
GPIO.output(24,GPIO.HIGH)
GPIO.output(15, GPIO.HIGH) 

app = Flask(__name__)

@app.route("/check_on", methods=["POST"])
def check_on():
  global check
  check = False
  tr = Thread(target=check)
  tr.start()
  return "ok"

@app.route("/check_off", methods=["POST"])
def check_off():
  global check
  check = False
  return "ok"

@app.route("/fill", methods=["POST"])
def filling():
  fill()
  return "ok"

@app.route("/change", methods=["POST"])
def change():
    #enable gpio pins
  PINOUT = 24
  GPIO.setmode(GPIO.BCM)
  GPIO.setwarnings(False)
  GPIO.setup(PINOUT,GPIO.out)
  #enable out pump
  GPIO.output(PINOUT, GPIO.LOW)
  time.sleep(20) 
  GPIO.output(PINOUT, GPIO.HIGH)
  fill()
  return "ok"


@app.route("/", methods=["GET"])
def home():
  return render_template("button.html", title="Button", name="Evan Rohan")