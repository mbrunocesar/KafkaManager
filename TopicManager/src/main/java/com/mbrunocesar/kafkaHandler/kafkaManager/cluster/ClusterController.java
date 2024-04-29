package com.mbrunocesar.kafkaHandler.kafkaManager.cluster;

import com.mbrunocesar.kafkaHandler.kafkaManager.auth.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clusters")
public class ClusterController {

    private final ClusterService clusterService;
    private final AuthService authService;

    public ClusterController(ClusterService clusterService, AuthService authService) {
        this.clusterService = clusterService;
        this.authService = authService;
    }

    @GetMapping("/")
    public ClusterEntity getStatus(
            @RequestHeader(name = "Authorization", required = false) String bearerToken) throws Exception {
        authService.isValidToken(bearerToken);

        return this.clusterService.getStatus();
    }

}
