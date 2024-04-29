package com.mbrunocesar.kafkaHandler.kafkaManager.cluster;

import com.mbrunocesar.kafkaHandler.kafkaManager.auth.AuthService;
import com.mbrunocesar.kafkaHandler.kafkaManager.cluster.dto.NodeEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clusters")
public class ClusterController {

    private final ClusterService clusterService;
    private final AuthService authService;

    public ClusterController(ClusterService clusterService, AuthService authService) {
        this.clusterService = clusterService;
        this.authService = authService;
    }

    @GetMapping("/status")
    public ClusterEntity getStatus(
            @RequestHeader(name = "Authorization", required = false) String bearerToken) throws Exception {
        authService.isValidToken(bearerToken);

        return this.clusterService.getStatus();
    }

    @GetMapping("/nodes")
    public NodeEntity[] getNodes(
            @RequestHeader(name = "Authorization", required = false) String bearerToken) throws Exception {
        authService.isValidToken(bearerToken);

        return this.clusterService.getNodes();
    }

}
