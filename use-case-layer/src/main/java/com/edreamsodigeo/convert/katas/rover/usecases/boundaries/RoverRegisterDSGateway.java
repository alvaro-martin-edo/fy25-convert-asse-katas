package com.edreamsodigeo.convert.katas.rover.usecases.boundaries;

import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.RoverRequestDS;
import com.edreamsodigeo.convert.katas.rover.usecases.boundaries.dataaccess.RoverResponseDS;

public interface RoverRegisterDSGateway {

    boolean existsByName(String name);

    void save(RoverRequestDS roverRequestDS);

    RoverResponseDS findByName(String name);

}
