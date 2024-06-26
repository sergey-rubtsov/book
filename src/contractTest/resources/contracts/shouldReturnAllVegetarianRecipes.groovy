package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return one vegetarian recipe"

    request {
        method GET()
        urlPath("/api/recipes") {
            queryParameters {
                parameter("vegetarian", "true")
                parameter("page", "1")
                parameter("size", "1")
            }
        }
    }

    response {
        status 200
        body([
                [
                        instructions: "Mix all fruits together",
                        servings: 2,
                        title: "Fruit Salad",
//                        ingredients : [
//                                [name: "Apple", "category": "VEGETARIAN"],
//                                [name: "Banana", "category": "VEGETARIAN"]
//                        ]
                ]
        ]
        )
        headers {
            contentType(applicationJson())
        }
    }
}

