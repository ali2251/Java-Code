package apiProcessing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ali Azam on 02/12/2016.
 */

public class JsonProcessor implements Serializable {

    private static final long serialVersionUID = 42L;


    private  ArrayList<Country> countries;
    private  ArrayList<String> countryNames;
    private  ArrayList<String> countryCodes;

    /**
     * Method get get all countries name
     * @return An array list of countries name
     */
    public ArrayList<String> getCountryNames() {
        return countryNames;
    }

    /**
     * Method get get all countries code
     * @return An array list of countries code
     */
    public ArrayList<String> getCountryCodes() {
        return countryCodes;
    }

    /**
     * Method that returns a List of Object Wrapper containing a country's GDP information
     * @param country - Country to search for.
     * @param startDate - From (e.g. yyyy)
     * @param endDate - To (e.g. yyyy)
     * @return A object wrapper containing A country together with it's GDP
     */
    public List<ObjectWrapper> getGDP(String country, int startDate, int endDate) {

        endDate = endDate + 1;

        List<ObjectWrapper> local = new ArrayList<ObjectWrapper>();
        int localStartdate = startDate;

        for (Country c: countries) {

            for(ObjectWrapper ow: c.getGdp()) {

                if(ow.getCountry().equals(country) &&  Integer.parseInt(ow.getDate().substring(1,5)) == localStartdate && localStartdate < endDate ) {
                    local.add(ow);
                    localStartdate ++ ;
                }

            }

        }

        return local;
    }

    /**
     * Method that returns a List of Object Wrapper containing a country's GDPGrowth information
     * @param country - Country to search for.
     * @param startDate - From (e.g. yyyy)
     * @param endDate - To (e.g. yyyy)
     * @return A object wrapper containing A country together with it's GDP Growth
     */
    public List<ObjectWrapper> getGDPGrowth(String country, int startDate, int endDate) {

        endDate = endDate + 1;

        List<ObjectWrapper> local = new ArrayList<ObjectWrapper>();
        int localStartdate = startDate;

        for (Country c: countries) {

            for(ObjectWrapper ow: c.getGdpGrowth()) {

                if(ow.getCountry().equals(country) &&  Integer.parseInt(ow.getDate().substring(1,5)) == localStartdate && localStartdate < endDate ) {
                    local.add(ow);
                    localStartdate ++ ;
                }

            }

        }

        return local;
    }

    /**
     * Method that returns a List of Object Wrapper containing a country's GDP Per Capita information
     * @param country - Country to search for.
     * @param startDate - From (e.g. yyyy)
     * @param endDate - To (e.g. yyyy)
     * @return A object wrapper containing A country together with it's GDP Per Capita
     */
    public List<ObjectWrapper> getGDPPerCapita(String country, int startDate, int endDate) {

        endDate = endDate + 1;

        List<ObjectWrapper> local = new ArrayList<ObjectWrapper>();
        int localStartdate = startDate;

        for (Country c: countries) {

            for(ObjectWrapper ow: c.getGdpPerCapita()) {

                if(ow.getCountry().equals(country) &&  Integer.parseInt(ow.getDate().substring(1,5)) == localStartdate && localStartdate < endDate ) {
                    local.add(ow);
                    localStartdate ++ ;
                }

            }

        }

        return local;
    }

    /**
     * Method that returns a List of Object Wrapper containing a country's Inflation information
     * @param country - Country to search for.
     * @param startDate - From (e.g. yyyy)
     * @param endDate - To (e.g. yyyy)
     * @return A object wrapper containing A country together with it's Inflation
     */
    public List<ObjectWrapper> getInflation(String country, int startDate, int endDate) {

        endDate = endDate + 1;

        List<ObjectWrapper> local = new ArrayList<ObjectWrapper>();
        int localStartdate = startDate;

        for (Country c: countries) {

            for(ObjectWrapper ow: c.getInflation()) {

                if(ow.getCountry().equals(country) &&  Integer.parseInt(ow.getDate().substring(1,5)) == localStartdate && localStartdate < endDate ) {
                    local.add(ow);
                    localStartdate ++ ;
                }

            }

        }

        return local;
    }

    /**
     * Method that returns a List of Object Wrapper containing a country's Unemployment information
     * @param country - Country to search for.
     * @param startDate - From (e.g. yyyy)
     * @param endDate - To (e.g. yyyy)
     * @return A object wrapper containing A country together with it's Unemployment
     */
    public List<ObjectWrapper> getUnemployment(String country, int startDate, int endDate) {

        endDate = endDate + 1;

        List<ObjectWrapper> local = new ArrayList<ObjectWrapper>();
        int localStartdate = startDate;

        for (Country c: countries) {

            for(ObjectWrapper ow: c.getUnemployment()) {

                if(ow.getCountry().equals(country) &&  Integer.parseInt(ow.getDate().substring(1,5)) == localStartdate && localStartdate < endDate ) {
                    local.add(ow);
                    localStartdate ++ ;
                }

            }

        }

        return local;
    }

    /**
     * Method that returns a List of Object Wrapper containing a country's Population information
     * @param country - Country to search for.
     * @param startDate - From (e.g. yyyy)
     * @param endDate - To (e.g. yyyy)
     * @return A object wrapper containing A country together with it's Population
     */
    public List<ObjectWrapper> getPopulation(String country, int startDate, int endDate) {

        endDate = endDate + 1;

        List<ObjectWrapper> local = new ArrayList<ObjectWrapper>();
        int localStartdate = startDate;

        for (Country c: countries) {

            for(ObjectWrapper ow: c.getPopulation()) {

                if(ow.getCountry().equals(country) &&  Integer.parseInt(ow.getDate().substring(1,5)) == localStartdate && localStartdate < endDate ) {
                    local.add(ow);
                    localStartdate ++ ;
                }

            }

        }

        return local;
    }


    @Override
    public String toString() {
        return "JsonProcessor{" +
                "countries=" + countries +
                ", countryNames=" + countryNames +
                ", countryCodes=" + countryCodes +
                '}';
    }

    public ArrayList<Country> getCountries() {
        return countries;
    }

    /**
     * Constructor
     */
    public JsonProcessor() {

        countries = new ArrayList<Country>();
        countryCodes = new ArrayList<String>(JsonParser.getPopularCountryCodes());
        countryNames = new ArrayList<String>(JsonParser.getPopularCountryNames());
        downloadForAllCountries();

        System.out.println( toString());

    }

    /**
     * Method to cache data, Downloading all data from 1990 to 2015 for all countries
     */
    private void downloadForAllCountries() {

        for(String country: countryCodes ) {

            countries.add(new Country(country,1995,2016));

        }
    }


}