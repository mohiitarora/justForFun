# Implementation of classic arcade game Pong

import simplegui
import random

# initialize globals - pos and vel encode vertical info for paddles
WIDTH = 600
HEIGHT = 400       
BALL_RADIUS = 20
PAD_WIDTH = 8
PAD_HEIGHT = 80
HALF_PAD_WIDTH = PAD_WIDTH / 2
HALF_PAD_HEIGHT = PAD_HEIGHT / 2
ball_vel = [5, -0.2]
ball_pos = [WIDTH / 2, HEIGHT / 2]
paddle1_pos = HEIGHT/2.0
paddle2_pos = HEIGHT/2.0
paddle1_vel = 0
paddle2_vel = 0
score1 = 0
score2 = 0

# helper function that spawns a ball by updating the 
# ball's position vector and velocity vector
# if right is True, the ball's velocity is upper right, else upper left
def ball_init(right):
    global ball_pos, ball_vel # these are vectors stored as lists
    ball_vel = [5, 0.2]
    if not right:
        ball_vel[0] = - ball_vel[0] 
    ball_pos = [WIDTH / 2, HEIGHT / 2]
    

# define event handlers

def new_game():
    global paddle1_pos, paddle2_pos, paddle1_vel, paddle2_vel  # these are floats
    global score1, score2  # these are ints
    score1 = 0
    score2 = 0
    paddle1_vel = 0
    paddle2_vel = 0
    paddle1_pos = HEIGHT/2.0
    paddle2_pos = HEIGHT/2.0
    ball_init(True)

def draw(c):
    global score1, score2, paddle1_pos, paddle2_pos, ball_pos, ball_vel
 
    # update paddle's vertical position, keep paddle on the screen
    #if not( paddle1_pos - 40 < 0 or paddle1_pos + 40 > HEIGHT):
    paddle1_pos += paddle1_vel
    #if not ( paddle2_pos - 40 < 0 or paddle2_pos + 40 > HEIGHT):
    paddle2_pos += paddle2_vel
    # draw mid line and gutters
    c.draw_line([WIDTH / 2, 0],[WIDTH / 2, HEIGHT], 1, "White")
    c.draw_line([PAD_WIDTH, 0],[PAD_WIDTH, HEIGHT], 1, "White")
    c.draw_line([WIDTH - PAD_WIDTH, 0],[WIDTH - PAD_WIDTH, HEIGHT], 1, "White")
    
    # draw paddles
    c.draw_line([4, paddle2_pos - 40], [4, paddle2_pos + 40],PAD_WIDTH, "White")
    c.draw_line([WIDTH - PAD_WIDTH +4,paddle1_pos - 40], [WIDTH - PAD_WIDTH +4, paddle1_pos + 40], PAD_WIDTH, "White")
    # update ball
    
    #and ((ball_pos[0] > paddle1_pos - 40) or (ball_pos[0] < paddle1_pos + 40)  )
    if ball_pos[0] <= BALL_RADIUS:
        if ((ball_pos[1] > paddle2_pos - 40) and (ball_pos[1] < paddle2_pos + 40)):
            ball_vel[0] += 1
            ball_vel[1] += 1
            ball_vel[0] = - ball_vel[0]
        else:
            score2 += 1
            ball_init(True)
        
    if  ball_pos[0] >= (WIDTH -1) - BALL_RADIUS:
        if ((ball_pos[1] > paddle1_pos - 40) and (ball_pos[1] < paddle1_pos + 40)):
            ball_vel[0] += 1
            ball_vel[1] += 1
            ball_vel[0] = - ball_vel[0]
        else:
            score1 += 1
            ball_init(False)
        
    if ball_pos[1] <= BALL_RADIUS or ball_pos[1] >= (HEIGHT -1) - BALL_RADIUS:
        ball_vel[1] = - ball_vel[1]
    
    ball_pos[0] += ball_vel[0]
    ball_pos[1] += ball_vel[1]
    
    
    
    # draw ball and scores
    c.draw_circle(ball_pos, BALL_RADIUS, 2, "Blue", "White")
    c.draw_text(str(score1), (60, 100), 36, "White")
    c.draw_text(str(score2), (WIDTH - 60, 100), 36, "White")
    
        
def keydown(key):
    global paddle1_vel, paddle2_vel
    paddle1_vel = 0
    paddle2_vel = 0
    
    if key==simplegui.KEY_MAP["down"]:
        paddle1_vel += 2
    elif chr(key) == "S":
        paddle2_vel += 2
    elif key==simplegui.KEY_MAP["up"]:
        paddle1_vel -= 2
    elif chr(key)=='W':
        paddle2_vel -= 2
     
   
   
def keyup(key):
    global paddle1_vel, paddle2_vel
    paddle1_vel = 0
    paddle2_vel = 0
    
    
# create frame
frame = simplegui.create_frame("Pong", WIDTH, HEIGHT)
frame.set_draw_handler(draw)
frame.set_keydown_handler(keydown)
frame.set_keyup_handler(keyup)
frame.add_button("Reset", new_game)


# start frame
frame.start()
