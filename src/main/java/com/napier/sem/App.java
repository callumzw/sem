package com.napier.sem;
// used to do stuff
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
// This prints reports of Countries, Cities, Capitals and Populations...
// pog
public class App {
    public static void main(String[] args) {

        if (args.length < 1)
        {
            // Connect to database...
            Database.connect("localhost:33060");
        }
        else
        {
            Database.connect(args[0]);
        }
/*
       ArrayList<Country> countries = Country.topCountryCont(5,"Africa");
       Country.displayCountry(countries);


        ArrayList<Capital> capitals = Capital.topCapitalRegion(5,"Eastern Asia");
        Capital.displayCapital(capitals);


       ArrayList<City> cities = City.topCityDistrict(5,"Zuid-Holland");
       City.displayCity(cities);
       */


        System.out.println("Populations in Continents");
        ArrayList<Population> populations = Population.popContinent();
        Population.displayPopulation(populations);
        populations = Population.popRegion();
        Population.displayPopulation(populations);
        populations = Population.popCountry();
        Population.displayPopulation(populations);

        // Disconnect from database
        Database.disconnect();



    }

}
