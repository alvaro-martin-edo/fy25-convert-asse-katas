# fy25-convert-asse-katas
Code katas used during the mentorship program to prepare the ASSE promotion in the Convert area

# Rover Kata Implementation

Implementation using clean architecture, based on this article: https://www.baeldung.com/spring-boot-clean-architecture

## Description of the problem to be solved

A squad of robotic rovers are to be landed by NASA on a plateau on Mars.

This plateau, which is curiously rectangular, must be navigated by the rovers so that their onboard cameras can get a complete view of the surrounding terrain to send back to Earth.

Your task is to develop an API that moves the rovers around on the plateau.

In this API, the plateau is represented as a 10x10 grid, and a rover has state consisting of two parts:

its position on the grid (represented by an X,Y coordinate pair)
the direction the compass is facing (represented by a letter, one of  'N', 'S', 'E', 'W')
the starting position of the rover is '0:0:N'

### Input

The input to the program is a string of one-character move commands:

'L' and 'R' rotate the direction the rover is facing
'M' moves the rover one grid square forward in the direction it is currently facing.
If a rover reaches the end of the plateau, it wraps around the end of the grid.

### Output

The program's output is the final position of the rover after all the move commands have been executed. The position is represented as a coordinate pair and a direction, joined by colons to form a string. For example: a rover whose position is `2:3:W` is at square (2,3), facing west.

### Interface

There are no restrictions on the design of the public interface.

A public interface to the API could look something like this:

`public class MarsRover
{
public string Execute(string command);
}`

### Rules:
The rover receives a char array of commands e.g.'RMMLM' and returns the finishing point after the moves e.g. '2:1:N'
The rover wraps around if it reaches the end of the grid.


## 1st evolution

### Optional Rover Method
The method for execute in the MarsRover class is optional. Rovers may have different approaches to processing instructions, allowing flexibility in implementation.

### Extended Commands with Repetition
Commands now extend support to repeated operations. For example:
2M2L3M means:
Move forward 2 steps.
Turn left twice.
Move forward 3 steps.

## Command Validation
Ensure that commands are valid before execution:
Reject invalid formats (e.g., 2X or different characters).
Provide feedback for errors (e.g., unsupported or malformed commands).
Multiple Planets with Configurable Sizes

Expand beyond Mars to support multiple planets, each with different grid sizes:
Venus: 15x15 grid.
Mercury: 5x5 grid.
Jupiter: 200x200 grid.

Deployment to Specific Planet and Position
Rovers must be deployable to a specific position and orientation on a chosen planet. For example:
Deploy a rover to position (3, 4) facing North on Mars(or Venus or whatever).
Ensure deployments are within the bounds of the planet’s grid.
If necessary create a class diagram or one or more sequence diagrams.


## 2nd evolution

### Collision Management
Ensure rovers do not collide with:
Other rovers.

### Obstacles.
If a collision is imminent:
Either prevent the movement or handle it gracefully (e.g., by stopping or rerouting).

### Astronaut API
Define and implement the API for astronauts(execute and deploy operations)
Only 1 astronaut at a time, no concurrency
Choose between 1 or 2 or do both

1. Deploying Multiple Rovers to the Same Planet
   Allow multiple rovers to operate on the same planet.
   Each rover must:
   Have a unique identifier or name.
   Avoid starting in the same position as another rover.
2. Grid with Obstacles
   Enhance the planet’s grid to include obstacles like mountains, water, craters(free creation of planets).
   Rovers must:
   Detect obstacles in their path.
   Avoid collisions with obstacles during movement.
