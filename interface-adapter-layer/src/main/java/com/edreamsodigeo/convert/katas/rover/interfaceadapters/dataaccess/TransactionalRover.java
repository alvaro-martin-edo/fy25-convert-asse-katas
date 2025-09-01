package com.edreamsodigeo.convert.katas.rover.interfaceadapters.dataaccess;

import com.edreamsodigeo.convert.katas.rover.interfaceadapters.impl.RoverDataMapper;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.impl.RoverResponseDSImpl;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.RoverRegisterDSGateway;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.RoverRequestDS;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.RoverResponseDS;

public class TransactionalRover implements RoverRegisterDSGateway {

    private final RoverRepository repository;

    public TransactionalRover(RoverRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    @Override
    public void save(RoverRequestDS roverRequestDS) {
        if (repository.existsByName(roverRequestDS.getName())) {
            throw new IllegalArgumentException("Rover with name " + roverRequestDS.getName() + " already exists.");
        }
        RoverDataMapper roverDataMapper = new RoverDataMapper(
                roverRequestDS.getName(),
                roverRequestDS.getHorizontalPosition(),
                roverRequestDS.getVerticalPosition(),
                roverRequestDS.getDirection(),
                roverRequestDS.getDeployedAt(),
                roverRequestDS.getLastCommandAt(),
                roverRequestDS.isActive()
        );
        repository.save(roverDataMapper);
    }

    @Override
    public RoverResponseDS findByName(String name) {
        RoverDataMapper roverDataMapper = repository.findByName(name);
        if (roverDataMapper == null) {
            throw new IllegalArgumentException("Rover with name " + name + " not found.");
        }
        return new RoverResponseDSImpl(
                roverDataMapper.getName(),
                roverDataMapper.getDirection(),
                roverDataMapper.getHorizontalPosition(),
                roverDataMapper.getVerticalPosition(),
                roverDataMapper.getDeployedAt(),
                roverDataMapper.getLastCommandAt(),
                roverDataMapper.isActive()
        );
    }
}
