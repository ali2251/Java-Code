package apiProcessing;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ali on 03/12/2016.
 */
public class Country implements Serializable{

    private static final long serialVersionUID = 42L;

    private  List<ObjectWrapper> gdp;
    private  List<ObjectWrapper> gdpPerCapita;
    private  List<ObjectWrapper> inflation;
    private  List<ObjectWrapper> unemployment;
    private  List<ObjectWrapper> population;
    private  List<ObjectWrapper> gdpGrowth;



    @Override
    public String toString() {
        return "Country{" +
                "gdp=" + gdp +
                ", gdpPerCapita=" + gdpPerCapita +
                ", inflation=" + inflation +
                ", unemployment=" + unemployment +
                ", population=" + population +
                ", gdpGrowth=" + gdpGrowth +
                '}';
    }

    /**
     * @return return List of object wrapper containing information for gdp
     */
    public List<ObjectWrapper> getGdp() { return gdp; }

    /**
     * @return return List of object wrapper containing information for gdp per capita
     */
    public List<ObjectWrapper> getGdpPerCapita() {
        return gdpPerCapita;
    }

    /**
     * @return return List of object wrapper containing information for inflation
     */
    public List<ObjectWrapper> getInflation() {
        return inflation;
    }

    /**
     * @return return List of object wrapper containing information for unemployment
     */
    public List<ObjectWrapper> getUnemployment() {
        return unemployment;
    }

    /**
     * @return return List of object wrapper containing information for population
     */
    public List<ObjectWrapper> getPopulation() {
        return population;
    }

    /**
     * @return return List of object wrapper containing information for gdp growth
     */
    public List<ObjectWrapper> getGdpGrowth() {
        return gdpGrowth;
    }

    /**
     * Method to initialize the caching
     */
    public Country( String name, int startDate,  int endDate) {

        download(name, startDate, endDate);

    }

    /**
     * Method to cache data
     * @param country country's code
     * @param startDate start date
     * @param endDate end date
     */
    public void download(String country, int startDate, int endDate) {

        try {

         gdp = (List<ObjectWrapper>) JsonParser.getGdpYears(country,startDate,endDate);
         gdpPerCapita = (List<ObjectWrapper>) JsonParser.getGdpPerCapitaYears(country,startDate,endDate);
         inflation = (List<ObjectWrapper>) JsonParser.getInflationYears(country,startDate,endDate);
         unemployment = (List<ObjectWrapper>) JsonParser.getUnemploymentYears(country,startDate,endDate);
         population = (List<ObjectWrapper>) JsonParser.getPopulationYears(country,startDate,endDate);
         gdpGrowth = (List<ObjectWrapper>) JsonParser.getGdpGrowthYears(country,startDate,endDate);

        } catch (Exception e) {


        }

    }



}
