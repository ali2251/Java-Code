package apiProcessing;

import org.json.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import java.io.Serializable;

/**
 * Created by Ali on 27/11/2016.
 */

public class JsonParser implements Serializable {

    private static final long serialVersionUID = 42L;

    private static transient JSONArray array2;
    private static transient JSONArray array;
    private static transient JSONObject second;

    /**
     * Method to get popular countries name
     * @return An array list of popular countries name
     */
    public static ArrayList<String> getPopularCountryNames() {

        String[] arrayOfNames = {"China", "America","India","Brazil","Mexico","UK","Argentina","Japan","Indonesia","Pakistan","Germany","Vietnam","Turkey","Malaysia","Egypt","South Africa","Canada","Russia","Australia"};

        ArrayList<String> listToReturn = new ArrayList<String>(Arrays.asList(arrayOfNames));

        return listToReturn;

    }

    /**
     * Method to get popular countries code
     * @return An array list of popular countries code
     */
    public static ArrayList<String> getPopularCountryCodes() {

        String[] arrayOfCodes = {"CHN", "USA","IND","BRA","MEX","GBR","ARG","JPN","IDN","PAK","DEU","VNM","TUR","MYS","EGY","ZAF","CAN","RUS","AUS"};

        ArrayList<String> listToReturn = new ArrayList<String>(Arrays.asList(arrayOfCodes));

        return listToReturn;
    }

    public static List<String> getCountryNames() {

       return getCountries("name");
    }

    public static List<String> getCountryCodes() {

          return getCountries("id");
    }



    private static List<String> getCountries(String request) {


         try {
            URL url1 = new URL("http://api.worldbank.org/countries/?format=json&per_page=304");
            HttpURLConnection connection1 = (HttpURLConnection) url1.openConnection();
            BufferedReader bf = new BufferedReader(
                    new InputStreamReader(connection1.getInputStream()));
            StringBuilder stringb = new StringBuilder();
            String line1;
            while ((line1 = bf.readLine()) != null) {
                stringb.append(line1);
            }
            bf.close();

            JSONArray array = new JSONArray(stringb.toString());

            JSONArray array2 = new JSONArray(array.get(1).toString());
            JSONObject first = new JSONObject(array.getJSONObject(0));
            JSONObject second = new JSONObject(array2.get(303).toString());
            ArrayList<String> listToReturn =  new ArrayList<String>();

            for(int i = 0; i < 304; ++i) {

                listToReturn.add((new JSONObject(array2.get(i).toString()).get(request)).toString());

            }

            return listToReturn;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    /**
     * Method to get data on gdp growth for a specific country
     * @param country country's code (e.g. CHN, USA, GBR .....)
     * @param startDate start date (format: yyyy)
     * @param endDate start date (format: yyyy)
     * @return A List of Object Wrapper containing data for gdp growth for a specific country, one object wrapper is the data for one year
     */
    public static List<ObjectWrapper> getGdpGrowthYears(String country, int startDate, int endDate) {

        ArrayList<ObjectWrapper> list = new ArrayList<ObjectWrapper>();
        String date = startDate + ":" + startDate;
        int currentYear = 2016;
        if(endDate > 2015) endDate = 2016;
        else {
            endDate += 1;
        }

        while (startDate != endDate) {

            ObjectWrapper ow = getGdpGrowth(country, date);
            if(ow != null) {
                list.add(ow);
            }
            startDate++;
            date = startDate + ":" + startDate;
        }
        return list;

    }


    public static ObjectWrapper getGdpGrowth(String country, String date) {

        ObjectWrapper gdp = getIndicator(country, date, "NY.GDP.MKTP.KD.ZG", "GDP Growth in %");
        if (gdp != null) return gdp;

        return null;

    }

    /**
     * Method to get data on population for a specific country
     * @param country country's code (e.g. CHN, USA, GBR .....)
     * @param startDate start date (format: yyyy)
     * @param endDate start date (format: yyyy)
     * @return A List of Object Wrapper containing data for population for a specific country, one object wrapper is the data for one year
     */
    public static List<ObjectWrapper> getPopulationYears(String country, int startDate, int endDate) {

        ArrayList<ObjectWrapper> list = new ArrayList<ObjectWrapper>();
        String date = startDate + ":" + startDate;
        if(endDate > 2015) endDate = 2016;
        else {
            endDate += 1;
        }

        while (startDate != endDate) {

            ObjectWrapper ow = getPopulation(country, date);
            if(ow != null) {
                list.add(ow);
            }
            startDate++;
            date = startDate + ":" + startDate;
        }
        return list;


    }



    public static ObjectWrapper getPopulation(String country, String date) {

        ObjectWrapper gdp = getIndicator(country, date, "SP.POP.TOTL", "Population Total");
        if (gdp != null) return gdp;

        return null;

    }


    /**
     * Method to get data on unemployment for a specific country
     * @param country country's code (e.g. CHN, USA, GBR .....)
     * @param startDate start date (format: yyyy)
     * @param endDate start date (format: yyyy)
     * @return A List of Object Wrapper containing data for unemployment for a specific country, one object wrapper is the data for one year
     */
    public static List<ObjectWrapper> getUnemploymentYears(String country, int startDate, int endDate) {
        ArrayList<ObjectWrapper> list = new ArrayList<ObjectWrapper>();
        String date = startDate + ":" + startDate;
        if(endDate > 2014) endDate = 2014;
        else {
            endDate += 1;
        }

        while (startDate != endDate) {

            ObjectWrapper ow = getUnemployment(country, date);
            if(ow != null) {
                list.add(ow);
            }
            startDate++;
            date = startDate + ":" + startDate;
        }


        return list;

    }



    public static ObjectWrapper getUnemployment(String country, String date) {

        ObjectWrapper gdp = getIndicator(country, date, "SL.UEM.TOTL.ZS", "Unemployment in %");
        if (gdp != null) return gdp;

        return null;
    }



    /**
     * Method to get data on inflation for a specific country
     * @param country country's code (e.g. CHN, USA, GBR .....)
     * @param startDate start date (format: yyyy)
     * @param endDate start date (format: yyyy)
     * @return A List of Object Wrapper containing data for inflation for a specific country, one object wrapper is the data for one year
     */
    public static List<ObjectWrapper> getInflationYears(String country, int startDate, int endDate) {
        if(startDate < 1996) {startDate = 1996;}

        ArrayList<ObjectWrapper> list = new ArrayList<ObjectWrapper>();
        String date = startDate + ":" + startDate;
        if(endDate > 2015) endDate = 2016;
        else {
            endDate += 1;
        }

        if(country.equals("ARG") && endDate > 2013) {
            endDate = 2014;
        }

        while (startDate != endDate) {

            ObjectWrapper ow = getInflation(country, date);
            if(ow != null) {
                list.add(ow);
            }
            startDate++;
            date = startDate + ":" + startDate;
        }
        return list;
    }




    public static ObjectWrapper getInflation(String country, String date) {

        ObjectWrapper gdp = getIndicator(country, date, "FP.CPI.TOTL.ZG", "Inflation % annual");
        if (gdp != null) return gdp;

        return null;
    }


    /**
     * Method to get data on gdp per capita for a specific country
     * @param country country's code (e.g. CHN, USA, GBR .....)
     * @param startDate start date (format: yyyy)
     * @param endDate start date (format: yyyy)
     * @return A List of Object Wrapper containing data for gdp per capita for a specific country, one object wrapper is the data for one year
     */


    public static List<ObjectWrapper> getGdpPerCapitaYears(String country, int startDate, int endDate) {

        ArrayList<ObjectWrapper> list = new ArrayList<ObjectWrapper>();
        String date = startDate + ":" + startDate;
        if(endDate > 2015) endDate = 2016;
        else {
            endDate += 1;
        }

        while (startDate != endDate) {

            ObjectWrapper ow = getGdpPercapita(country, date);
            if(ow != null) {
                list.add(ow);
            }
            startDate++;
            date = startDate + ":" + startDate;
        }
        return list;
    }


    /**
     * Method to get data for GDP per capita for a specific country for one year only
     * @param country
     * @param date
     * @return An object wrapper containing data for GDP per capita for a specific country
     */


    public static ObjectWrapper getGdpPercapita(String country, String date) {

        ObjectWrapper gdp = getIndicator(country, date, "NY.GDP.PCAP.CD", "GDPPerCapita");
        if (gdp != null) return gdp;

        return null;

    }

    /**
     * Method to get data on GDP for a specific country
     * @param country country's code (e.g. CHN, USA, GBR .....)
     * @param startDate start date (format: yyyy)
     * @param endDate start date (format: yyyy)
     * @return A List of Object Wrapper containing data for GDP for a specific country, one object wrapper is the data for one year
     */
    public static List<ObjectWrapper> getGdpYears(String country, int startDate, int endDate) {

        ArrayList<ObjectWrapper> list = new ArrayList<ObjectWrapper>();
        String date = startDate + ":" + startDate;
        if(endDate > 2015) endDate = 2016;
        else {
            endDate += 1;
        }

        while (startDate != endDate) {

            ObjectWrapper ow = getGdp(country, date);
            if(ow != null) {
                list.add(ow);
            }
            startDate++;
            date = startDate + ":" + startDate;
        }

        return list;

    }


    public static ObjectWrapper getGdp(String country, String date) {

        ObjectWrapper gdp = getIndicator(country, date, "NY.GDP.MKTP.CD", "GDP");
        if (gdp != null) return gdp;

        return null;

    }



    private static ObjectWrapper getIndicator(String country, String date, String indicator, String description) {
        try {


            URL url1 = new URL("http://api.worldbank.org/countries/" + country + "/indicators/" + indicator + "?format=json&date=" + date);
            HttpURLConnection connection1 = (HttpURLConnection) url1.openConnection();
            BufferedReader bf = new BufferedReader(
                    new InputStreamReader(connection1.getInputStream()));
            StringBuilder stringb = new StringBuilder();
            String line1;
            while ((line1 = bf.readLine()) != null) {
                stringb.append(line1);
            }
            bf.close();

             array = new JSONArray(stringb.toString());

             array2 = new JSONArray(array.get(1).toString());

          //  JSONObject first = new JSONObject(array.getJSONObject(0));


            second = new JSONObject(array2.get(0).toString());


            //long gdpValue = Long.parseLong(String.valueOf(second.get("value")));

            Object object = second.get("value");

            if(object != null ) {

                return new ObjectWrapper(country, date.substring(4), object, description);

            } else {
                return null;
            }




        } catch (Exception e) {

            return null;
            //e.printStackTrace();
        }
       // return null;
    }

}
