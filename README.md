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
    "name": "dateRandom",
    "type": "DateTemplate",
    "generator": {
      "type": "Random",
      "format": "yyyy-MM-dd"
    }
  },
  {
    "name": "dateSequence",
    "type": "DateTemplate",
    "generator": {
      "type": "Sequence",
      "format": "yyyy-MM-dd"
    }
  },
  {
    "name": "dateCurrent",
    "type": "DateTemplate",
    "generator": {
      "type": "Current",
      "format": "yyyy-MM-dd"
    }
  },
  {
    "name": "datetimeRandom",
    "type": "DatetimeTemplate",
    "generator": {
      "type": "Random",
      "format": "yyyy-MM-dd'T'HH:mm:ss.SSSxx"
    }
  },
  {
    "name": "datetimeSequence",
    "type": "DatetimeTemplate",
    "generator": {
      "type": "Sequence",
      "format": "yyyy-MM-dd'T'HH:mm:ss.SSSxx"
    }
  },
  {
    "name": "datetimeCurrent",
    "type": "DatetimeTemplate",
    "generator": {
      "type": "Current",
      "format": "yyyy-MM-dd'T'HH:mm:ss.SSSxx"
    }
  },
  {
    "name": "doubleRandom",
    "type": "DoubleTemplate",
    "generator": {
      "type": "Random",
      "format": "000000.00"
    }
  },
  {
    "name": "doubleSequence",
    "type": "DoubleTemplate",
    "generator": {
      "type": "Sequence",
      "format": "000000.00"
    }
  },
  {
    "name": "sourceRandom",
    "type": "FromSourceTemplate",
    "generator": {
      "type": "Random",
      "source": {
        "type": "FromFile",
        "filepath": "filename.txt"
      }
    }
  },
  {
    "name": "sourceSequence",
    "type": "FromSourceTemplate",
    "generator": {
      "type": "Sequence",
      "source": {
        "type": "FromValues",
        "values": [
          "value1",
          "value2"
        ]
      }
    }
  },
  {
    "name": "integerRandom",
    "type": "IntegerTemplate",
    "generator": {
      "type": "Random",
      "format": "000000"
    }
  },
  {
    "name": "integerSequence",
    "type": "IntegerTemplate",
    "generator": {
      "type": "Sequence",
      "format": "000000"
    }
  },
  {
    "name": "timeRandom",
    "type": "TimeTemplate",
    "generator": {
      "type": "Random",
      "format": "HH:mm:ss.SSSxx"
    }
  },
  {
    "name": "timeSequence",
    "type": "TimeTemplate",
    "generator": {
      "type": "Sequence",
      "format": "HH:mm:ss.SSSxx"
    }
  },
  {
    "name": "timeCurrent",
    "type": "TimeTemplate",
    "generator": {
      "type": "Current",
      "format": "HH:mm:ss.SSSxx"
    }
  },
  {
    "name": "stringRandom",
    "type": "StringTemplate",
    "generator": {
      "type": "Random"
    },
    "format": "\\w+"
  },
  {
    "name": "stringSequence",
    "type": "StringTemplate",
    "generator": {
      "type": "Sequence"
    },
    "format": "\\d+"
  },
  {
    "name": "uuid",
    "type": "UUIDTemplate",
    "allowMultiple": true,
    "generator": {
      "type": "UUIDGenerator"
    }
  }
]
```
