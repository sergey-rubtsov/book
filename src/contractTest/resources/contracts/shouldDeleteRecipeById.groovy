package contracts
import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should delete a recipe by id"

    request {
        method DELETE()
        urlPath("/api/recipes") {
            queryParameters {
                parameter("id", "1")
            }
        }
    }

    response {
        status 200
    }
}

