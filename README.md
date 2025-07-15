# Muffin wallet

## Database structure

```plantuml
@startuml E/R

entity muffin_wallet {
    * id: uuid
    --
    * amount: bigdecimal
    * owner: varchar(40)
    * created_at: datetime
    * updated_at: datetime
}

entity muffin_transaction {
    * id: uuid
    --
    * amount: bigdecimal
    * from: uuid <<FK>>
    * to: uuid <<FK>>
    * created_at: datetime
}

muffin_transaction }|..|| muffin_wallet

@enduml
```