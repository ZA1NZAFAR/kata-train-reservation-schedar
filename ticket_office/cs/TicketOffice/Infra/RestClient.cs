using Newtonsoft.Json;
using System.Net.Http.Headers;
using TicketOffice.Domain;

namespace TicketOffice.Infra;

public class RestClient : IRestClient
{
    private readonly HttpClient _httpClient;

    public RestClient()
    {
        _httpClient = new HttpClient();
    }

    public async Task<string> GetBookingReference()
    {
        var response = await _httpClient.GetAsync("http://127.0.0.1:8082/booking_reference");
        response.EnsureSuccessStatusCode();
        return await response.Content.ReadAsStringAsync();
    }

    public async Task<string> GetTrainData(string trainId)
    {
        var response = await _httpClient.GetAsync($"http://127.0.0.1:8081/data_for_train/{trainId}");
        response.EnsureSuccessStatusCode();
        return await response.Content.ReadAsStringAsync();
    }

    public async Task MakeReserveration(string trainId, string bookingReference, IEnumerable<string> seats)
    {
        var reservation = new Dictionary<string, object>
        {
            { "train_id", trainId },
            { "seats", seats },
            { "booking_reference", bookingReference }
        };
        var reservationContent = new StringContent(JsonConvert.SerializeObject(reservation));
        reservationContent.Headers.ContentType = new MediaTypeHeaderValue("application/json");
        var response = await _httpClient.PostAsync("http://127.0.0.1:8081/reserve", reservationContent);
        response.EnsureSuccessStatusCode();
    }
}
