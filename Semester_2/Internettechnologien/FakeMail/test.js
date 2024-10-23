var t  =require('./test.json')

console.log(t.answers[1].intent+' '+t.answers[1].answer)

data = '[{"name": "Tjorven", "age": "20"}]'

var mydata = JSON.parse(data);
console.log(mydata[0].name + " is " + mydata[0].age + " years old.")