package io.neilshirsat.catan;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class APITests {

    private static Logger Log = LoggerFactory.getLogger(APITests.class);

    private static API api;

    public static void main(String[] args) {
        api = new API((started)->{
            Log.info("The API has started on PORT 6584");
        });
        api.initialize();
        api.startServer();
        Scanner input = new Scanner(System.in);
        do {
            input.next();
        } while (!input.next().equals("close"));
    }

}
