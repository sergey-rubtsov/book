package contracts
import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should create a new recipe"

    request {
        method POST()
        url "/api/recipes"
        body([
                title       : "Vegetarian Pizza",
                instructions: "Bake the pizza.",
                servings    : 2,
                ingredients : [
                        [name: "Cheese", "category": "VEGETARIAN"],
                        [name: "Tomato", "category": "VEGETARIAN"]
                ]
        ])
        headers {
            contentType(applicationJson())
        }
    }

    response {
        status 201
        body([
                id          : 11,
                title       : "Vegetarian Pizza",
                instructions: "Bake the pizza.",
                servings    : 2,
                ingredients : [
                        [name: "Cheese", "category": "VEGETARIAN"],
                        [name: "Tomato", "category": "VEGETARIAN"]
                ]
        ])
        headers {
            contentType(applicationJson())
        }
    }
}

