# Kafka templated data producer

This is to produce kafka messages to specified topic based on the template data file. This allows to configure template field and possible data generation option.

## Supported template configurations
### Data types
* Boolean
* String 
* Integer
* Double
* Date
* Time
* Datetime

### Data generation options

* Random generated
* Sequencially from given data source
* Randomly from given data source
* Incremental from start with given increment
* Current value (for Date,Time and Datetime)

### Data source options

* From given array or values
* From specified file each items in separate lines

## Example template configuration
```
[
  {
    "name": "date",
    "type": "DateTemplate"
  },
  {
    "name": "datetime",
    "type": "DatetimeTemplate"
  },
  {
    "name": "double",
    "type": "DoubleTemplate"
  },
  {
    "name": "source",
    "type": "FromSourceTemplate"
  },
  {
    "name": "integer",
    "type": "IntegerTemplate"
  },
  {
    "name": "time",
    "type": "TimeTemplate"
  },
  {
    "name": "uuid",
    "type": "UUIDTemplate",
    "allowMultiple": true
  }
]
```
