package com.napier.sem;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Population
{
    /**
     * Name of area.
     */
    public String name;

    /**
     * Total population
     */
    public long totalPop;

    /**
     * City Population
     */
    public long cityPop;

    /**
     *  Rural Population
     */
    public long ruralPop;

    // Methods
    public static ArrayList<Population> popContinent()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = Database.getCon().createStatement();
            // Create string for SQL statement
            String countrySelect =
                    "SELECT Continent, SUM(DISTINCT country.Population), SUM(city.Population), SUM(DISTINCT country.Population)-SUM(city.Population) "
                            + "FROM country JOIN city ON city.CountryCode=country.Code "
                            + "GROUP BY Continent ORDER BY SUM(country.Population) DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(countrySelect);
            // Return new employee if valid.
            ArrayList<Population> populations = new ArrayList<Population>();
            // Check one is returned
            while (rset.next())
            {
                Population population = new Population();
                population.name = rset.getString("Continent");
                population.totalPop = rset.getLong("SUM(DISTINCT country.Population)");
                population.cityPop = rset.getLong ("SUM(city.Population)");
                population.ruralPop = rset.getLong ("SUM(DISTINCT country.Population)-SUM(city.Population)");
                populations.add(population);
            }
            return populations;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    public static ArrayList<Population> popRegion()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = Database.getCon().createStatement();
            // Create string for SQL statement
            String countrySelect =
                    "SELECT Region, SUM(DISTINCT country.Population), SUM(city.Population), SUM(DISTINCT country.Population)-SUM(city.Population) "
                            + "FROM country JOIN city ON city.CountryCode=country.Code "
                            + "GROUP BY Region ORDER BY SUM(country.Population) DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(countrySelect);
            // Return new employee if valid.
            ArrayList<Population> populations = new ArrayList<Population>();
            // Check one is returned
            while (rset.next())
            {
                Population population = new Population();
                population.name = rset.getString("Region");
                population.totalPop = rset.getLong("SUM(DISTINCT country.Population)");
                population.cityPop = rset.getLong("SUM(city.Population)");
                population.ruralPop = rset.getLong("SUM(DISTINCT country.Population)-SUM(city.Population)");
                populations.add(population);
            }
            return populations;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    public static ArrayList<Population> popCountry()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = Database.getCon().createStatement();
            // Create string for SQL statement
            String countrySelect =
                    "SELECT country.Name, SUM(DISTINCT country.Population), SUM(city.Population), SUM(DISTINCT country.Population)-SUM(city.Population) "
                            + "FROM country JOIN city ON city.CountryCode=country.Code "
                            + "GROUP BY country.Name ORDER BY SUM(country.Population) DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(countrySelect);
            // Return new employee if valid.
            ArrayList<Population> populations = new ArrayList<Population>();
            // Check one is returned
            while (rset.next())
            {
                Population population = new Population();
                population.name = rset.getString("country.Name");
                population.totalPop = rset.getLong("SUM(DISTINCT country.Population)");
                population.cityPop = rset.getLong("SUM(city.Population)");
                population.ruralPop = rset.getLong("SUM(DISTINCT country.Population)-SUM(city.Population)");
                populations.add(population);
            }
            return populations;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    public static void displayPopulation(ArrayList<Population> populations)
    {
        System.out.println(String.format("%-18s %-14s %-14s %-14s", "Name", "Total", "City", "Rural"));
        for (Population pop : populations)
        {
            String pop_str =
                    String.format("%-18s %-14s %-14s %-14s",
                            pop.name, pop.totalPop, pop.cityPop, pop.ruralPop);
            System.out.println(pop_str);
        }
        System.out.println("\n");
    }
}
