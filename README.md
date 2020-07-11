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
{
  "fields": [
    {
      "name": "timeValue",
      "type": "datetime",
      "format": "yyyy-MM-dd'T'HH:mm:ss.SSSxx",
      "generator": "now"
    },
    {
      "name": "integerValue",
      "type": "int",
      "format": "00000",
      "generator": "incremental",
      "start": 100,
      "increment": 10
    },
    {
      "name": "doubleValue",
      "type": "double",
      "format": "00000.00",
      "generator": "random",
      "min": -1000.00,
      "max":  1000.00
    },
    {
      "name": "stringValue1",
      "type": "string",
      "generator": "random",
      "source": "list",
      "list": [ "Value1", "Value2", "Value3", "Value4", "Value5" ]
    },
    {
      "name": "stringValue1",
      "type": "string",
      "generator": "sequence",
      "source": "file",
      "file": "filePath"
    }
  ]
}
```
