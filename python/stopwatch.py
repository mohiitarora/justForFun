# "Stopwatch: The Game"
import simplegui

# define global variables
t = 0
m = 0
interval = 100
time = ""
attempts = 0
total_attempts = 0
score = ""
stop = True

# define helper function format that converts time
# in tenths of seconds into formatted string A:BC.D
def format(tm):
    global time, t, m
    s = tm / 10
    ss = tm % 10
    if(s == 59 and ss == 9):
        m += 1
        t = 0
    if ( m == 9 and t % 60 == 0):
        m = 0
        t = 0
    if(s < 10):
        time = str(m)+":0"+str(int(s))+":"+str(ss)
    else :
        time = str(m)+":"+str(int(s))+":"+str(ss)
    
# define event handlers for buttons; "Start", "Stop", "Reset"
def start_button_handler():
    global stop
    timer.start()
    stop = False
    
def stop_button_handler():
    global t, attempts, total_attempts, stop
    if(not stop):
        if( t % 10 == 0):
            attempts +=1
            total_attempts +=1
        else:
            total_attempts +=1
        timer.stop()
        stop = True
        
    
def reset_button_handler():
    global attempts, total_attempts, stop
    timer.stop()
    stop = True
    total_attempts = 0
    attempts = 0
    format(0)
# define event handler for timer with 0.1 sec interval
def tick():
    global t
    t += 1
    format(t)


# define draw handler
def draw_handler(canvas):
    canvas.draw_text(time, (60, 100), 36, "White")
    canvas.draw_text(str(attempts) +"/"+ str(total_attempts), (150, 30), 25, "Green")
    
    
# create frame
frame = simplegui.create_frame("Stopwatch", 200, 200)
frame.add_button("Start", start_button_handler)
frame.add_button("Stop", stop_button_handler)
frame.add_button("Reset", reset_button_handler)


# register event handlers
frame.set_draw_handler(draw_handler)
timer = simplegui.create_timer(interval, tick)

# start frame
frame.start()