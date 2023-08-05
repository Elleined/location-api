package com.elleined.locationapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlConfig.*;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional(propagation = Propagation.SUPPORTS)
public class LocationInitialValuesPopulatorTest {

    @Test
    @SqlGroup({
            @Sql(scripts = {
                    "classpath:sql/provinceData.sql",
                    "classpath:sql/cityData.sql",
                    "classpath:sql/baranggayData.sql"
            }, config = @SqlConfig(transactionMode = TransactionMode.DEFAULT)),
    })
    void locationInitialValues() {

    }
}
