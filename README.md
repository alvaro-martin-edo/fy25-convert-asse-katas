# fy25-convert-asse-katas
Code catas used during the mentorship program to prepare the ASSE promotion in the Convert area

## Features

- Define the size of the plateau and place obstacles.
- Control the rover's movement using commands.
- View the plateau map with obstacles and the rover's position.
- Handle invalid commands and obstacle collisions gracefully.

## Available Commands

### Movement Commands
- **M**: Move the rover forward in the direction it is currently facing.
- **L**: Rotate the rover 90 degrees to the left.
- **R**: Rotate the rover 90 degrees to the right.

### Combined Commands
- You can chain commands together, e.g., `MMRMLM`, to execute multiple actions in sequence.

### Special Commands
- **map**: Display the plateau map, showing obstacles (rover position coming soon).
- **exit**: Exit the application.

## How to Run

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd fy25-convert-asse-katas
   ```

2. Build the project using Maven:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   java -jar target/fy25-convert-asse-katas.jar
   ```

4. Follow the prompts to define the plateau, add obstacles, and control the rover.

## Example Usage

1. Define the plateau size:
   ```
   Enter the plateau size (x:y):
   10:10
   ```

2. Add obstacles:
   ```
   Enter the number of obstacles:
   2
   Enter obstacle coordinates (x:y):
   3:3
   Enter obstacle coordinates (x:y):
   5:5
   ```

3. Control the rover:
   ```
   Enter command:
   M
   Result: 0:1:N
   Enter command:
   R
   Result: 0:1:E
   Enter command:
   map
   ```

4. Exit the application:
   ```
   Enter command:
   exit
   ```

## Resources

- [SIMPLE KATA](https://www.codurance.com/katas/mars-rover)
- [PATRON STATE](https://refactoring.guru/es/design-patterns/state/java/example)
- [PATRON COMMAND](https://refactoring.guru/es/design-patterns/command)
- [Expresiones regulares](https://regex101.com/)
- [Datos por consola](https://programacion365.com/ingreso-de-datos/)

## Testing

Run the test suite using Maven:
```bash
mvn test
```

The test suite includes:
- Unit tests for rover movement and rotation.
- Tests for obstacle handling.
- Command execution tests.


## Next Steps
- Improve commands to allow other formats (e.g., 2MR4MLM).
- Implement the rover's position display on the plateau map.
- Add more advanced obstacle handling (e.g., multiple rovers).

