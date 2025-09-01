package com.edreamsodigeo.convert.katas.rover.interfaceadapters.dataaccess;

import com.edreamsodigeo.convert.katas.rover.interfaceadapters.impl.PlanetGridDataMapper;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.PlanetGridRegisterDSGateway;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.PlanetGridRequestDS;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.PlanetGridResponseDS;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.impl.PlanetGridResponseDSImpl;

public class TransactionalPlanetGridRegister implements PlanetGridRegisterDSGateway {

    private final PlanetGridRepository planetGridRepository;

    public TransactionalPlanetGridRegister(PlanetGridRepository planetGridRepository) {
        this.planetGridRepository = planetGridRepository;
    }

    @Override
    public boolean existsByName(String name) {
        return planetGridRepository.existsByName(name);
    }

    @Override
    public void save(PlanetGridRequestDS planetGridRequestDS) {
        if (planetGridRepository.existsByName(planetGridRequestDS.getPlanetName())) {
            throw new IllegalArgumentException("Planet with name " + planetGridRequestDS.getPlanetName() + " already exists.");
        }
        PlanetGridDataMapper planetGridDataMapper = new PlanetGridDataMapper(
                planetGridRequestDS.getPlanetName(),
                planetGridRequestDS.getWidth(),
                planetGridRequestDS.getHeight()
        );
        planetGridRepository.save(planetGridDataMapper);
    }

    @Override
    public PlanetGridResponseDS findByPlanetName(String name) {
        PlanetGridDataMapper planetGridDataMapper = planetGridRepository.findByName(name);
        if (planetGridDataMapper == null) {
            throw new IllegalArgumentException("Planet grid with name " + name + " not found.");
        }
        return new PlanetGridResponseDSImpl(
                planetGridDataMapper.getWidth(),
                planetGridDataMapper.getHeight(),
                planetGridDataMapper.getPlanetName()
        );
    }

    @Override
    public PlanetGridResponseDS findByRoverName(String roverName) {
        PlanetGridDataMapper planetGridDataMapper = planetGridRepository.findByRoverName(roverName);
        if (planetGridDataMapper == null) {
            throw new IllegalArgumentException("Planet grid with rover " + roverName + " not found.");
        }
        return new PlanetGridResponseDSImpl(
                planetGridDataMapper.getWidth(),
                planetGridDataMapper.getHeight(),
                planetGridDataMapper.getPlanetName()
        );
    }
}
