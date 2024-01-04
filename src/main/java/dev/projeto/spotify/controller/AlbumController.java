package dev.projeto.spotify.controller;

import dev.projeto.spotify.client.Album;
import dev.projeto.spotify.client.AlbumSpotifyClient;
import dev.projeto.spotify.client.AuthSpotifyClient;
import dev.projeto.spotify.client.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/spotify/api")
public class AlbumController {

    private final AuthSpotifyClient authSpotifyClient;
    private final AlbumSpotifyClient albumSpotifyClient;

    public AlbumController(AuthSpotifyClient authSpotifyClient, AlbumSpotifyClient albumSpotifyClient) {
        this.authSpotifyClient = authSpotifyClient;
        this.albumSpotifyClient = albumSpotifyClient;
    }

    @GetMapping("/albums")
    public ResponseEntity<List<Album>> helloWorld() {
        var request = new LoginRequest("client_credentials", "aad91253dbc742a0a676bf8c7ef51d5f", "311b31956edb4f048f516ca602ade686");
        var token = authSpotifyClient.login(request).getAccessToken();

        var response = albumSpotifyClient.getReleases("Bearer " + token);

        return ResponseEntity.ok(response.getAlbums().getItems());
    }
}
