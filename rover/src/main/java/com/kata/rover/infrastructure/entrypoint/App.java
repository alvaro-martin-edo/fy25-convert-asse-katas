package com.kata.rover.infrastructure.entrypoint;

import com.kata.rover.application.RoverController;
import com.kata.rover.infrastructure.adapter.commandline.input.*;
import com.kata.rover.infrastructure.adapter.commandline.output.DirectionRenderer;
import com.kata.rover.infrastructure.adapter.commandline.output.PositionRenderer;
import com.kata.rover.model.Direction;
import com.kata.rover.model.Plateau;
import com.kata.rover.model.Position;
import com.kata.rover.model.command.Command;

import java.util.List;

public class App {
    private static final int PLATEAU_WIDTH = 10;
    private static final int PLATEAU_HEIGHT = 10;

    private static final Position INITIAL_POSITION = new Position(0, 0, Direction.NORTH);

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide the command input as the first argument.");
            return;
        }

        String input = args[0];

        CommandListParser commandListParser = buildCommandListParser();
        List<Command> commands = commandListParser.parseCommands(input);

        Plateau plateau = new Plateau(PLATEAU_WIDTH, PLATEAU_HEIGHT); // Example plateau size
        RoverController roverController = new RoverController(INITIAL_POSITION, plateau);

        roverController.executeCommandList(commands);

        DirectionRenderer directionRenderer = new DirectionRenderer();
        PositionRenderer positionRenderer = new PositionRenderer(directionRenderer);

        Position finalPosition = roverController.getPosition();
        System.out.println(positionRenderer.render(finalPosition));
    }

    private static CommandListParser buildCommandListParser() {
        Tokenizer tokenizer = new Tokenizer();
        SimpleCommandParser simpleCommandParser = new SimpleCommandParser();
        RepeatedCommandParser repeatedCommandParser = new RepeatedCommandParser(simpleCommandParser);
        CommandParserFactory commandParserFactory = new CommandParserFactory(
                simpleCommandParser, repeatedCommandParser);

        return new CommandListParser(commandParserFactory, tokenizer);
    }
}
