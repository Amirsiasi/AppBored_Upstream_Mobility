# DefaultApi

All URIs are relative to *https://www.boredapi.com/api*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getRandomActivity**](DefaultApi.md#getRandomActivity) | **GET** /activity | Ruft eine zufällige Aktivität ab |


<a name="getRandomActivity"></a>
# **getRandomActivity**
> ActivityResponse getRandomActivity(type)

Ruft eine zufällige Aktivität ab

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://www.boredapi.com/api");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    String type = "type_example"; // String | Der Typ der Aktivität
    try {
      ActivityResponse result = apiInstance.getRandomActivity(type);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#getRandomActivity");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **type** | **String**| Der Typ der Aktivität | [optional] |

### Return type

[**ActivityResponse**](ActivityResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Erfolgreiche Antwort |  -  |

