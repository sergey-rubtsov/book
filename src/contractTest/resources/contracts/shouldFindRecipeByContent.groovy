package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return one vegetarian recipe"

    request {
        method GET()
        urlPath("/api/recipes") {
            queryParameters {
                parameter("content", "Cook beef")
                parameter("page", "0")
                parameter("size", "1")
            }
        }
    }

    response {
        status 200
        body([
                [
                        instructions: "Cook beef, add potatoes and carrots",
                        servings: 5,
                        title: "Beef Stew"
                ]
        ]
        )
        headers {
            contentType(applicationJson())
        }
    }
}

