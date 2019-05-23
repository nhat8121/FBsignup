Feature: United and Spirit Airlines ticket booking functionality

@Smoke
Scenario Outline: Checking prices on United and Spirit airlines "<dataset>"

	Given user at united homepage
	When user click on ticket type "<row>" "<ttypecol>"
	And user wait for "1"
	And user enter "ufrombox" "<row>" "<fromcol>"
	And user wait for "1"
	And user enter "utobox" "<row>" "<tocol>"
	And user wait for "1"
	And user click "udeparturedatebox"
	And user wait for "1"
	And user select date "<uddate>"
	And user wait for "1"
#	And user click "ureturndatebox"
#	And user wait for "1"
#	And user select date "<urdate>"
#	And user wait for "1"
	And user select travelers "utravelers" "<row>" "<travelerscol>"
	And user wait for "1"
	And user click "uticketclass"
	And user select ticket class "<row>" "<classcol>"
	And user wait for "1"
	And user click "ufindflightsbutton"
	And user wait for "10"
	And user print page title
	And user print united tickets "uprice1" "uprice2" "uprice3"
	And user navigate to "spirithomepage"
	And user wait for "1"
	And user print page title
	And user click on ticket type "<row>" "<ttypecol>"
	And user wait for "1"
	And user select "afrombox" "<row>" "<fromcol>"
	And user wait for "1"
	And user select "atobox" "<row>" "<tocol>"
	And user wait for "1"
	And user enter "adeparturedatebox" "<row>" "<ddatecol>"
	And user wait for "1"
#	And user enter "areturndatebox" "<row>" "<rdatecol>"
#	And user wait for "1"
	And user select "atravelers" "<row>" "<travelerscol>"
	And user wait for "1"
	And user click "asearchbutton"
	And user wait for "10"
	And user print page title
	And user print spirit tickets "aprice1" "aprice2" "aprice3"
	Then user will validate "unitedlow" "spiritlow"

		Examples:
		|dataset	|row	|ttypecol	|fromcol	|tocol		|ddatecol	|rdatecol	|travelerscol	|classcol	|uddate			|urdate			|
		|test1		|1		|0			|1			|2			|3			|4			|5				|6			|udepartdate1	|ureturndate1	|
		|test2		|2		|0			|1			|2			|3			|4			|5				|6			|udepartdate2	|ureturndate2	|
#		|test3		|3		|0			|1			|2			|3			|4			|5				|6			|udepartdate3	|ureturndate3	|