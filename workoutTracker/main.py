
#made by your boys, Evan Rohan and Cole Schaefer
#11-5-2021
def main():
  #If the user has already used this program they can just begin appending to their files that is already made instead of entering all of the starting information again
  past = input("Have you already entered starting information?(Y/N): ")
  if past == 'n' or past =='N':
    name = input("What is your name?: ")
    workout_type = input("Is your goal to cut, bulk, or maintain?: ")
    #validate the goal
    while workout_type != 'cut' and workout_type != 'bulk' and workout_type !='maintain':
      workout_type = input("please only enter cut, bulk, or maintain: ")
    height = input("How tall are you? (feet/inches): ")
    height_val = height.split("/")
    #Validate the height
    while int(height_val[0]) < 0 or int(height_val[1]) < 0:
      height = input("please enter a real height (feet/inches): ")
    weight = float(input("What is your starting weight?: ")) 
    today = ask_for_date()
    file = input("What would you like the file to be called to save your data?: ")
    outfile = open(file,'w')
    #Format starting information
    outfile.write(f'Name: {name}\n')
    outfile.write(f'Workout Type: {workout_type}\n')
    outfile.write(f'Height: {height}\tWeight: {weight} lbs\n')
    outfile.write(f'Starting date: {today}\n')
    outfile.write('\n')
    outfile.close()
  #If data has already been entered ask for the name of the outfile
  else:
    file = input("please re-enter the name of the file to save the data too: ")
  #Print the menu
  choice = menu_valid()
  while choice != 4:
    #add_workout
    if choice == 1:
      add_workout()
    #nutrition_options
    #add_meal
    elif choice == 2:
      add_meal()
    #weigh_in
    elif choice == 3:
      weigh_in()
    organize_file(file)
    choice = menu_valid()

#Print menu options
#User enters their choice and it gets validated
#Returns the choice  
def menu_valid():
  print("1 = add workout")
  print("2 = add meal")
  print("3 = weigh in")
  print("4 = quit")
  choice = int(input("What would you like to do? "))
  while choice < 0 or choice > 4:
    choice = int(input("Invalid input. What would you like to do? "))
  return choice
#Prompt user for a workout
#Save that workout to a file for later
#No return
def add_workout():
  today = ask_for_date()
  #What group was worked out
  workouttype = input("What did you workout today?(push,pull,legs): ")
  workouttype = workouttype.lower()
  #validate choice
  while workouttype != 'push' and workouttype != 'pull' and workouttype != 'legs' and workouttype != 'cardio':
    workouttype = input("Invalid: What did you workout today?(push,pull,legs,cardio): ")
    workouttype = workouttype.lower()
  cont = "y"
  #creat empty list
  exercises = []
  sets = []
  reps = []
  weight = []
  while cont != '':
    exercise = input("What exercise did you preform?: ")
    setss = int(input("How many sets did you do?: "))
    repss = int(input("How many reps did you do?: "))
    weights = float(input("How much weight did you use?: "))
    exercises.append(exercise)
    sets.append(setss)
    reps.append(repss)
    weight.append(weights)
    cont = input("Would you like to enter another exercise?(hit enter if ur done breh): ")
  workout = [exercises,sets,reps,weight]
  workoutfile = open("workout.txt",'a')
  workoutfile.write(f'{today[0]},{today[1]},{today[2]}+{workouttype}+')
  for i in range(len(exercises)):
    workoutfile.write(f'{workout[0][i]},{workout[1][i]},{workout[2][i]},{workout[3][i]}+')
  workoutfile.write('\n')
  #exercises + reps and weight?
  #date[group[exercise,set,reps,weight]]
  workoutfile.close()
#Prompts user to enter a meal and the time of the meal
#Saves that data to a file for reading later
#No return
def add_meal():
  today = ask_for_date()
  times = input("What time did you have your meal(24hr time 16:20): ")
  timeslist=times.split(":")
  hour = int(timeslist[0])
  minute = int(timeslist[1])
  while hour > 24 and hour < 0 and minute < 0 and minute >= 60:
    times = input("Invalid time: What time did you have your meal(24hr time 16:20): ")
    hour = int(timeslist[0])
    minute = int(timeslist[1])
  food = input("What did you eat (separated by commas): ")
  calories = float(input("How many calories did you consume?: "))
  mealfile = open("meal.txt",'a')
  mealfile.write(f'{today[0]},{today[1]},{today[2]}+{food}+{times}+{calories}\n')
  mealfile.close()
  
#Prompts user to enter weigh in
#Saves that data to a file for reading later
#No return
def weigh_in():
  today = ask_for_date()
  weight = float(input("How much did you weigh in as?: "))
  weightfile = open("weigh_in.txt",'a')
  weightfile.write(f'{today[0]},{today[1]},{today[2]},{weight}\n')
  weightfile.close()

#ask user for the date
#Validate that the date exists including leap years
#returns the date
def ask_for_date():
  month31 = [1,3,5,7,8,10,12]
  month30 = [4,6,9,11]
  feb = 2
  leapy = False
  
  today = input("What day would you like your data to be recorded for?(month/day/year): ")
  dates = today.split("/")
  for i in range(len(dates)):
    dates[i] = int(dates[i])
  if(dates[2] < 0):
    dates[2] = int(input("Year cannot be negative: "))
  elif(dates[2]%4 == 0 and dates[2]%100 != 0):
    leapy = True
  elif(dates[2]%4 == 0 and dates[2]%100 == 0 and dates[2]%400 == 0):
    leapy = True
  while dates[0] > 12 or dates[0] < 0:
    dates[0] = int(input("Invalid month, please enter a new month: "))
  if dates[0] in month31:
    while dates[1] > 31 or dates[1] < 0:
      dates[1] = int(input("Invalid day, please enter new day: "))
  elif dates[0] in month30:
    while dates[1] > 30 or dates[1] < 0:
      dates[1] = int(input("Invalid day, please enter new day: "))
  elif dates[0] == feb:
    if leapy:
      while dates[1] > 29 or dates[1] < 0:
        dates[1] = int(input("Invalid day, please enter new day: "))
    else:
      while dates[1] > 28 or dates[1] < 0:
        dates[1] = int(input("Invalid day, please enter new day: "))
  
  return dates
#read all of the infromation in the FileExistsError
#organize into one file that is easy to read
#no return
def organize_file(filename):
  mealfile = open("meal.txt",'r')
  weighin = open("weigh_in.txt",'r')
  workoutfile = open("workout.txt",'r')
  readfour = open(filename,'r')
  line = readfour.readline()
  name = line
  line = readfour.readline()
  worktype = line
  line = readfour.readline()
  hw = line
  line = readfour.readline()
  starting = line
  readfour.close()
    
  outfile = open(filename,'w')
  outfile.write(name)
  outfile.write(worktype)
  outfile.write(hw)
  outfile.write(starting)
  all_info = {}
  dateset = set()
  outfile.write('\n***************************************************\n\n')
  for line in weighin:
    info = line.rstrip("\n").split(',')
    dates = (info[0],info[1],info[2])
    weight = info[3]
    if dates in all_info:
      all_info[dates].append(weight)
    else:
      all_info[dates]=[weight]
      dateset.add(tuple(dates))
  for line1 in mealfile:
    info = line1.rstrip("+\n").split('+')
    dates1 = info[0].split(',')
    dates = (dates1[0],dates1[1],dates1[2])
    food = info[1]
    times = info[2]
    cals = info[3]
    if dates in all_info:
      all_info[dates].append([food,times,cals])
    else:
      all_info[dates]=[[food,times,cals]]
      dateset.add(tuple(dates))
  exercises = []
  for line2 in workoutfile:
    exercises.clear()
    info = line2.rstrip("+\n").split('+')
    dates1 = info[0].split(',')
    dates = (dates1[0],dates1[1],dates1[2])
    workouttype = info[1]
    for i in range(2,len(info)):
      exercises.append(info[i])
    if dates in all_info:
      all_info[dates].append([workouttype,[]+exercises])
    else:
      all_info[dates] = [[workouttype,[]+exercises]]
    dateset.add(tuple(dates))
  
  mealfile.close()
  weighin.close()
  workoutfile.close()
  #checks each date in dateset
  for times in dateset:
    outfile.write(f'{times[0]}/{times[1]}/{times[2]}\n ')
    information = all_info[times]
    for datatype in information:
      if type(datatype) is not list:
        outfile.write(f"\tWeight: {datatype} \n")
      else:
        if datatype[0] != 'pull' and datatype[0] != 'push' and datatype[0] !='legs':
          outfile.write(f"\tMeal:\n\t\tTime: {datatype[1]}\n\t\tFood: {datatype[0]}\n\t\tCalories: {datatype[2]}\n")
        else:
          outfile.write(f"\tWorkout: {datatype[0]}\n\t\tExercises: ")
          
          for i in range(1,len(datatype)):
            outfile.write(f"{datatype[i]} ")
          outfile.write("\n")


  outfile.close()


      
  
main()