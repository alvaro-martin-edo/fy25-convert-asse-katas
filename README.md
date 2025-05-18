# fy25-convert-asse-katas
Code catas used during the mentorship program to prepare the ASSE promotion in the Convert area

**Base kata:** [https://www.codurance.com/katas/simple-mars-rover](https://www.codurance.com/katas/simple-mars-rover)

**1st evolution**

**Optional Rover Method**  
The method for *execute* in the `MarsRover` class is optional. Rovers may have different approaches to processing instructions, allowing flexibility in implementation.

**Extended Commands with Repetition**  
Commands now extend support to repeated operations. For example:

* `2M2L3M` means:
    * Move forward 2 steps.
    * Turn left twice.
    * Move forward 3 steps.

**Command Validation**  
Ensure that commands are valid before execution:

* Reject invalid formats (e.g., `2X` or different characters).
* Provide feedback for errors (e.g., unsupported or malformed commands).

**Multiple Planets with Configurable Sizes**  
Expand beyond Mars to support multiple planets, each with different grid sizes:

* **Venus:** 15x15 grid.
* **Mercury:** 5x5 grid.
* **Jupiter:** 200x200 grid.

**Deployment to Specific Planet and Position**  
Rovers must be deployable to a specific position and orientation on a chosen planet. For example:

* Deploy a rover to position `(3, 4)` facing North on Mars(or Venus or whatever).
* Ensure deployments are within the bounds of the planet’s grid.

If necessary create a class diagram or one or more sequence diagrams.

**2nd evolution**

**Mandatory:**

**Collision Management**

* Ensure rovers do not collide with:
    * Other rovers.
    * Obstacles.
* If a collision is imminent:
    * Either prevent the movement or handle it gracefully (e.g., by stopping or rerouting).

**Astronaut API**

Define and implement the API for astronauts(execute and deploy operations)

Only 1 astronaut at a time, **no concurrency**

**Choose between 1 or 2 or do both**

**1\. Deploying Multiple Rovers to the Same Planet**

* Allow multiple rovers to operate on the same planet.
* Each rover must:
    * Have a unique identifier or name.
    * Avoid starting in the same position as another rover.

**2\. Grid with Obstacles**

* Enhance the planet’s grid to include obstacles like mountains, water, craters(free creation of planets).
* Rovers must:
    * Detect obstacles in their path.
    * Avoid collisions with obstacles during movement.

**3rd evolution**

**FastRover**

* Introduce a new type of rover with additional operations:
    * `slowDown`: Reduces its movement speed (fewer grid cells per move).
    * `goFaster`: Increases its movement speed (more grid cells per move).

**ClimbingRover**

* Introduce a specialized rover capable of climbing over certain obstacles (e.g., mountains).
* Define rules for what types of terrain the ClimbingRover can traverse and how it handles transitions.

**4th evolution**

Handle concurrency when multiple scientists can use the API at the same time

Identify the possible issues and tackle them