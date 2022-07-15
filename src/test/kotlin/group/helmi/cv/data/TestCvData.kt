package group.helmi.cv.data

class TestCvData {
    companion object {
        const val testDataValid = """
{
  "firstName": "Foo",
  "lastName": "Bar",
  "jobTitle": "Foo bartender",
  "contact": [
    {
      "icon": "MapMarker",
      "text": "Planet Earth",
      "disclose": true
    },
    {
      "icon": "Mobile",
      "text": "+00 (0)000 00000000",
      "href": "tel:+00000000000000",
      "disclose": true
    },
    {
      "icon": "At",
      "text": "test@example.com",
      "href": "mailto:test@example.com",
      "disclose": true
    },
    {
      "icon": "Globe",
      "text": "Website",
      "href": "https://github.com/helmid/",
      "disclose": true
    },
    {
      "icon": "Github",
      "text": "github.com/helmid",
      "href": "https://github.com/helmid/",
      "disclose": true
    },
    {
      "icon": "Globe",
      "text": "Some URL",
      "href": "https://github.com/helmid/",
      "disclose": true
    }
  ],
  "sections": [
    {
      "title": "About me section",
      "entries": [
        {
          "type": "about",
          "text": "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.",
          "country": "Lorem ipsum",
          "operatingRadius": "Lorem ipsum: Lorem ipsum",
          "workingMode": "Lorem ipsum dolor sit amet, consetetur sadipscing elitr",
          "barchart": [
            {
              "title": "Basic skill 1",
              "value": 89
            },
            {
              "title": "Basic skill 2",
              "value": 73
            },
            {
              "title": "Basic skill 3",
              "value": 70
            },
            {
              "title": "Basic skill 4",
              "value": 55
            },
            {
              "title": "Basic skill 5",
              "value": 40
            },
            {
              "title": "Basic skill 6",
              "value": 39
            }
          ],
          "bubbles": [
            {
              "title": "Skill 1",
              "value": 4.5
            },
            {
              "title": "Skill 2",
              "value": 6
            },
            {
              "title": "Skill 3",
              "value": 5.15
            },
            {
              "title": "Skill 4",
              "value": 3.3
            },
            {
              "title": "Skill 5",
              "value": 2
            }
          ]
        }
      ]
    },
    {
      "title": "History section",
      "entries": [
        {
          "type": "history",
          "start": "07/2015",
          "jobTitle": "Third Job",
          "customer": "Employer 2",
          "description": "Same Employer diferent job",
          "skills": [
            "Skill1",
            "Some longer skill, that needs some space",
            "Skill3",
            "Skill4",
            "SkillThatTakesSomeSpaceInOneLineSoWeCanCheckFormatting"
          ]
        },
        {
          "type": "history",
          "start": "01/2019",
          "end": "06/2019",
          "jobTitle": "Second Job",
          "customer": "Employer 2",
          "description": "Gathering more job experience",
          "skills": [
            "Skill"
          ]
        },
        {
          "type": "history",
          "start": "01/2015",
          "end": "12/2018",
          "jobTitle": "First Job",
          "customer": "Employer 1",
          "description": "Gathering job experience",
          "skills": []
        }
      ]
    },
    {
      "title": "Education section 1",
      "entries": [
        {
          "type": "education",
          "start": "01/2016",
          "end": "02/2016",
          "title": "Some Cert",
          "facility": "Some facility",
          "content": {
            "url": "https://github.com/helmid/",
            "text": "https://github.com/helmid/"
          }
        }
      ]
    },
    {
      "title": "Education section 2",
      "entries": [
        {
          "type": "education",
          "start": "2013",
          "end": "2015",
          "title": "Some more studies",
          "facility": "Some other university",
          "content": {
            "text": "Some more content"
          }
        },
        {
          "type": "education",
          "start": "2010",
          "end": "2013",
          "title": "Some studies",
          "facility": "Some university",
          "content": {
            "text": "Some content"
          }
        }
      ]
    },
    {
      "title": "",
      "entries": [
        {
          "type": "cols",
          "title": "Some more skills",
          "barchart": [
            {
              "title": "Test1",
              "value": 100
            },
            {
              "title": "Test2",
              "value": 85
            }
          ]
        }
      ]
    }
  ]
}
        """
        const val testDataInvalid = "{}"
    }
}