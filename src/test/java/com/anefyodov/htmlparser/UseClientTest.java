package com.anefyodov.htmlparser;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UseClientTest {

    public static final String HTTP_YA_RU = "http://ya.ru";
    public static final String server = "https://ndb.nal.usda.gov";
    private String url = server + "/ndb/search/list?maxsteps=6&format=Abridged&count=&max=50&sort=fd_s" +
            "&fgcd=&manu=&lfacet=&qlookup=&ds=&qt=&qp=&qa=&qn=&q=&ing=&offset=183950&order=asc";

//    @Test
//    public void shouldList() {
//        //final String server = "https://ndb.nal.usda.gov";
//        Client client = ClientBuilder.newClient();
//        WebTarget target = client.target(url);
//        Response response1 = target.request(MediaType.TEXT_HTML_TYPE).get();
//        System.out.println(response1.getStatus());
//        final String location = response1.getHeaderString("Location");
//        System.out.println(location);
//        System.out.println(response1.readEntity(String.class));
//    }
//
//    @Test
//    public void main() {
//        Client c  = ClientBuilder.newClient();
//        WebTarget target = c.target(HTTP_YA_RU);
//        target.property(ClientProperties.FOLLOW_REDIRECTS, Boolean.TRUE);
//        final Response response1 =
//                target.request(MediaType.TEXT_HTML_TYPE).get();
//        System.out.println(response1.getStatus());
//        final String location = response1.getHeaderString("Location");
//        System.out.println(location);
//        System.out.println(response1.readEntity(String.class));
//        final Response response = c.target(location).request(MediaType.TEXT_HTML_TYPE).get();
//        System.out.println(response.getStatus());
//        System.out.println(response.readEntity(String.class));
//        //String response = resource.get(String.class);
//    }

    @Test
    public void shouldReadPage() {
        final UseClient useClient = new UseClient();
        final String page = useClient.getPage(HTTP_YA_RU);
        System.out.println(page);
        assertThat(page).isNotNull();
    }

    @Test
    public void shouldReturnListForReadPageWhenPageWithListGiven() {
        final UseClient useClient = new UseClient();
        final String page = useClient.getPage(url);
        System.out.println(page);
        assertThat(page).isNotNull();
    }

    @Test
    public void shouldReadProduct() {
        String urlProd = "/ndb/foods/show/27582?fg=&manu=&lfacet=&format=Abridged&count=&max=50&offset=183950&sort" +
                "=fd_s" +
                "&order=asc&qlookup=&ds=&qt=&qp=&qa=&qn=&q=&ing=";
        final UseClient useClient = new UseClient();
        final String page = useClient.getPage(server+ urlProd);
        System.out.println(page);
        assertThat(page).isNotNull();

    }


}
