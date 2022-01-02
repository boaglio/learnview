
<template>

  <div class="container">
 
   <h1 class="c">New Question</h1>   
    <div class="card m-3"> 
     <div class="card-body">
      <Form id="questionForm" @submit="onSubmit" :validation-schema="schema" v-slot="{ errors }">

      <div class="row mb-3">
       <label for="category" class="col-sm-2 col-form-labell">category</label>
       <Field name="category"  v-model="question.category"  type="text" class="form-control" :class="{ 'is-invalid': errors.category }" />
       <div class="invalid-feedback">{{errors.category}}</div>
      </div>
      <div class="row mb-3">
       <label for="subcategory" class="col-sm-2 col-form-labell">subcategory</label>
       <Field name="subcategory"  v-model="question.subcategory"  type="text" class="form-control" :class="{ 'is-invalid': errors.subcategory }" />
       <div class="invalid-feedback">{{errors.subcategory}}</div>
      </div>
 
      <div class="row mb-3">
       <label for="exam" class="col-sm-2 col-form-labell">exam</label>
       <Field name="exam" v-model="question.exam"  type="text" class="form-control" :class="{ 'is-invalid': errors.exam }" />
       <div class="invalid-feedback">{{errors.exam}}</div>
      </div>
      <div class="row mb-3">
       <label for="tags" class="col-sm-2 col-form-labell">tags</label>
       <Field name="tags" v-model="question.tags" type="text" class="form-control" :class="{ 'is-invalid': errors.tags }" />
       <div class="invalid-feedback">{{errors.tags}}</div>
      </div>
      <div class="row mb-3">
       <label for="text" class="col-sm-2 col-form-labell">text</label>
       <Field name="text" v-model="question.text" type="text" as="textarea" class="form-control" :class="{ 'is-invalid': errors.text }" />
       <div class="invalid-feedback">{{errors.text}}</div>
      </div>
      <div class="row mb-3">
       <label for="code" class="col-sm-2 col-form-labell">code</label>
       <Field name="code" v-model="question.code" type="text" as="textarea" class="form-control" :class="{ 'is-invalid': errors.code }" />
       <div class="invalid-feedback">{{errors.code}}</div>
      </div>

      <!-- option 1 -->
      <div class="border p-2 border-secondary rounded">
       <div class="row mb-3">
        <label for="option1text" class="col-sm-2 col-form-labell">option 1 text</label>
        <div class="col-sm-10"><Field type="text"  v-model="question.option1text" name="option1text" class="form-control" :class="{ 'is-invalid': errors.option1text }"/></div>       
        <div class="invalid-feedback">{{errors.option1text}}</div>
       </div>
       <div class="row mb-3">
        <label for="option1text" class="col-sm-2 col-form-labell">option 1 answer</label>
        <div class="col-sm-10"><Field type="text"  v-model="question.option1answer" name="option1answer" class="form-control" :class="{ 'is-invalid': errors.option1text }" /></div>       
        <div class="invalid-feedback">{{errors.option1answer}}</div> 
       </div>
       <div class="row">
        <label for="option1text" class="col-sm-2 col-form-labell">option 1 correct answer ?</label>
        <div class="col-auto">
          <Field class="form-check-input" v-model="question.option1correct"  type="radio" name="option1correct" value="true"/>
          <label class="form-check-label" for="option1correcty">Yes</label>
        </div>
        <div class="col-auto">
          <Field class="form-check-input" v-model="question.option1correct" type="radio" name="option1correct" value="false" checked/>
          <label class="form-check-label" for="option1correctn">No</label>
        </div>
       </div>
      </div>

      <!-- option 2 -->
      <div class="border p-2 border-secondary rounded">
       <div class="row mb-3">
        <label for="option2text" class="col-sm-2 col-form-labell">option 2 text</label>
        <div class="col-sm-10"><Field type="text"  v-model="question.option2text" name="option2text" class="form-control" :class="{ 'is-invalid': errors.option2text }"/></div>       
        <div class="invalid-feedback">{{errors.option2text}}</div>
       </div>
       <div class="row mb-3">
        <label for="option2text" class="col-sm-2 col-form-labell">option 2 answer</label>
        <div class="col-sm-10"><Field type="text"  v-model="question.option2answer" name="option2answer" class="form-control" :class="{ 'is-invalid': errors.option2text }" /></div>       
        <div class="invalid-feedback">{{errors.option2answer}}</div> 
       </div>
       <div class="row">
        <label for="option2text" class="col-sm-2 col-form-labell">option 2 correct answer ?</label>
        <div class="col-auto">
          <Field class="form-check-input" v-model="question.option2correct" type="radio" name="option2correct" value="true"/>
          <label class="form-check-label" for="option2correcty">Yes</label>
        </div>
        <div class="col-auto">
          <Field class="form-check-input" v-model="question.option2correct" type="radio" name="option2correct" value="false" checked/>
          <label class="form-check-label" for="option2correctn">No</label>
        </div>
       </div>
      </div>

      <!-- option 3 -->
      <div class="border p-2 border-secondary rounded">
       <div class="row mb-3">
        <label for="option3text" class="col-sm-2 col-form-labell">option 3 text</label>
        <div class="col-sm-10"><Field type="text"  v-model="question.option3text" name="option3text" class="form-control" :class="{ 'is-invalid': errors.option3text }"/></div>       
        <div class="invalid-feedback">{{errors.option3text}}</div>
       </div>
       <div class="row mb-3">
        <label for="option3text" class="col-sm-2 col-form-labell">option 3 answer</label>
        <div class="col-sm-10"><Field type="text"  v-model="question.option3answer" name="option3answer" class="form-control" :class="{ 'is-invalid': errors.option3text }" /></div>       
        <div class="invalid-feedback">{{errors.option3answer}}</div> 
       </div>
       <div class="row">
        <label for="option3text" class="col-sm-2 col-form-labell">option 3 correct answer ?</label>
        <div class="col-auto">
          <Field class="form-check-input" type="radio" v-model="question.option3correct" name="option3correct" value="true"/>
          <label class="form-check-label" for="option3correcty">Yes</label>
        </div>
        <div class="col-auto">
          <Field class="form-check-input" type="radio" v-model="question.option3correct" name="option3correct" value="false" checked/>
          <label class="form-check-label" for="option3correctn">No</label>
        </div>
       </div>
      </div>

      <!-- option 4 -->
      <div class="border p-2 border-secondary rounded">
       <div class="row mb-3">
        <label for="option4text" class="col-sm-2 col-form-labell">option 4 text</label>
        <div class="col-sm-10"><Field type="text"  v-model="question.option4text" name="option4text" class="form-control" :class="{ 'is-invalid': errors.option4text }"/></div>       
        <div class="invalid-feedback">{{errors.option4text}}</div>
       </div>
       <div class="row mb-3">
        <label for="option4text" class="col-sm-2 col-form-labell">option 4 answer</label>
        <div class="col-sm-10"><Field type="text"  v-model="question.option4answer" name="option4answer" class="form-control" :class="{ 'is-invalid': errors.option4text }" /></div>       
        <div class="invalid-feedback">{{errors.option4answer}}</div> 
       </div>
       <div class="row">
        <label for="option4text" class="col-sm-2 col-form-labell">option 4 correct answer ?</label>
        <div class="col-auto">
          <Field class="form-check-input" type="radio" v-model="question.option4correct" name="option4correct" value="true"/>
          <label class="form-check-label" for="option4correcty">Yes</label>
        </div>
        <div class="col-auto">
          <Field class="form-check-input" type="radio" v-model="question.option4correct" name="option4correct" value="false" checked/>
          <label class="form-check-label" for="option4correctn">No</label>
        </div>
       </div>
      </div>

      <!-- option 5 -->
      <div class="border p-2 border-secondary rounded">
       <div class="row mb-3">
        <label for="option5text" class="col-sm-2 col-form-labell">option 5 text</label>
        <div class="col-sm-10"><Field type="text"  v-model="question.option5text" name="option5text" class="form-control" :class="{ 'is-invalid': errors.option5text }"/></div>       
        <div class="invalid-feedback">{{errors.option5text}}</div>
       </div>
       <div class="row mb-3">
        <label for="option5text" class="col-sm-2 col-form-labell">option 5 answer</label>
        <div class="col-sm-10"><Field type="text"  v-model="question.option5answer" name="option5answer" class="form-control" :class="{ 'is-invalid': errors.option5text }" /></div>       
        <div class="invalid-feedback">{{errors.option5answer}}</div> 
       </div>
       <div class="row">
        <label for="option5text" class="col-sm-2 col-form-labell">option 5 correct answer ?</label>
        <div class="col-auto">
          <Field class="form-check-input" type="radio" v-model="question.option5correct" name="option5correct" value="true"/>
          <label class="form-check-label" for="option5correcty">Yes</label>
        </div>
        <div class="col-auto">
          <Field class="form-check-input" type="radio" v-model="question.option5correct" name="option5correct" value="false" checked/>
          <label class="form-check-label" for="option5correctn">No</label>
        </div>
       </div>
      </div>

      <div class="row mb-3">
        <div class="input-group input-group-sm">
          <span class="input-group-text">explanation</span>
          <Field v-model="question.explanation" type="text" as="textarea" name="explanation" class="form-control flex-grow-1" aria-label="explanation" rows="10" />
        </div>
       <div class="invalid-feedback">{{errors.explanation}}</div>
      </div>
      <div class="row mb-3">
       <label for="sourceDescription" class="col-sm-2 col-form-labell">Source Description</label>
       <div class="col-sm-10"><Field type="text" v-model="question.sourceDescription" name="sourceDescription" class="form-control"/></div>       
       <div class="invalid-feedback">{{errors.sourceDescription}}</div>
      </div>      

      <div class="row mb-3">
       <label for="sourceLink" class="col-sm-2 col-form-labell">Source Link</label>
       <div class="col-sm-10"><Field type="text" v-model="question.sourceLink" name="sourceLink" class="form-control"/></div>       
       <div class="invalid-feedback">{{errors.sourceLink}}</div> 
      </div>   
 
      <div class="form-group"> 
        <button type="submit" @click="exportJSON()" class="btn btn-success">Export JSON</button>
        &nbsp;
        <button @click="fillSample1()" class="btn btn-info">Fill with sample 1</button>
        &nbsp;
        <button @click="fillSample2()" class="btn btn-info">Fill with sample 2</button>
        &nbsp;
        <router-link :to="{name: 'home'}"><button class="btn btn-primary">Back</button></router-link> 
      </div>
     </Form>
     <div id="responseJSON" style="display:none;text-align:left" >
      <h2>Please copy this JSON and submit a new Pull Request</h2>
      <hr/>
      <pre><div id="jsonCode">{{resJSON}}</div></pre>
      <hr/>
      <router-link :to="{name: 'home'}"><button class="btn btn-primary">Back</button></router-link> 
     </div>     
    </div>
  </div> 
 </div> 
</template>
<script>
 
import { Form, Field } from 'vee-validate';
import * as Yup from 'yup';

import question from '../domain/question/Question.js';
import questionService from '../domain/question/QuestionService.js';

export default {

  components: {
    Form,
    Field,
  },
 
    setup() {
        const schema = Yup.object().shape({
            title: Yup.string().required('Title is required'),
            category: Yup.string().required('Category is required'),
            subcategory: Yup.string() .required('SubCategory is required'),                
            exam: Yup.string() .required('Exam is required'),                
            tags: Yup.string().min(3, 'Tag must be at least 3 characters') .required('Tag is required'),                
            //code: Yup.string() .required('Code is required'),       
            text: Yup.string() .required('Text is required')
        });

        const onSubmit = values => {
            // display form values on success
            alert('SUCCESS!! :-)\n\n' + JSON.stringify(values, null, 4));
        }

        return {
            schema,
            onSubmit
        };
    },
   data() {
     return {
           resJSON:"",
           question:new question()
     }
   },

  methods: {

    exportJSON() { 
      console.log("export JSON question ...");
        this.service
        .exportJSON(this.question)
        .then(question => {
           this.resJSON = question;
           console.log(question);
           document.getElementById('questionForm').style.setProperty('display','none');
           document.getElementById('responseJSON').style.setProperty('display','block');
        });
        err => console.log(err);

    },

    fillSample1() {
      this.question = new question();
      console.log("Fill Sample 1...");
      this.question.category="Java";
      this.question.subcategory="initialization";
      this.question.exam="1Z0-819";
      this.question.tags="hard";
      this.question.text="Consider the classes shown below. Which is the correct sequence of the digits that will be printed when B is run?";
      this.question.code="class A{ \n\
   public A() { } \n\
   public A(int i) {   System.out.println(i );    } \n\
} \n\
class B{ \n\
   static A s1 = new A(1); \n\
   A a = new A(2); \n\
   public static void main(String[] args){\n\
      var b = new B(); \n\
      var a = new A(3); \n\
   } \n\
   static A s2 = new A(4); \n\
} ";
      this.question.option1text="1 ,2 ,3 4.";
      this.question.option1correct = "false";
      this.question.option2text="1 ,4, 2 ,3";
      this.question.option2correct = "true";
      this.question.option3text="3, 1, 2, 4";
      this.question.option3correct = "false";
      this.question.option4text="2, 1, 4, 3";
      this.question.option4correct = "false";
      this.question.option5text="2, 3, 1, 4";
      this.question.option5correct = "false";
      this.question.explanation=" 1. All static constants, variables, and blocks. Among themselves the order is the order in which they appear in the code. \
       This step is actually a part of \"class initialization\" rather than \"instance initialization\". Class initialization happens only if the class is being \
       used for the first time while being instantiated. For example, if you have invoked a static method of a class or accessed a static field of that class earlier\
        in the code, you have already used the class and the JVM would have performed initialization of this class at that time.\n\
         Thus, there wouldn't be any need to initialize the class if you instantiate an object of this class now. \n\
       Further, if the class has a superclass, then the JVM performs this step for the superclass first (if the superclass hasn't been initialized already)\
        and only after the superclass is initialized and static blocks are executed, does the JVM proceed with this class.\n\
         This process is followed recursively up to the java.lang.Object class.\n\
        2. All non static constants, variables, and blocks. Among themselves the order is the order in which they appear in the code. \n\
        3. Constructor. \n\
        Just like the class initialization, instance initialization also happens for the superclass first. That is, if the class has a superclass, \
        then the JVM takes steps 2 and 3 given above for the superclass first and only after the superclass's instance members are initialized, does the JVM \
        proceed with this class. This process is also followed recursively up to the java.lang.Object class."
      this.question.sourceLink="https://enthuware.com";
      this.question.sourceDescription="trial EXAM 1Z0-819";

    },

    fillSample2() {
      this.question = new question();
      console.log("Fill Sample 2...");
      this.question.category="Java";
      this.question.subcategory="enum";
      this.question.exam="1Z0-819";
      this.question.tags="enum easy";
      this.question.text="Which of the following lines will cause the compilation to fail??";
      this.question.code="public enum EnumA{ A, AA, AAA};  //1 \n\
public class TestClass //2 \n\
{ \n\
   public enum EnumB{ B, BB, BBB }; //3 \n\
   public static enum EnumC{ C, CC, CCC }; //4 \n\
   public TestClass() \n\
   { \n\
       enum EnumD{ D, DD, DDD } //5  \n\
   } \n\
\n\
   public void methodX() \n\
   { \n\
      public enum EnumE{ E, EE, EEE } //6 \n\
   } \n\
\n\
   public static void main(String[] args) //7 \n\
   { \n\
       enum EnumF{ F, FF, FFF}; //8 \n\
   } \n\
}"
      this.question.option1text="1, 2, or both depending on the file name.";
      this.question.option1answer="Since both EnumA and TestClass are public, they must be defined in a file with a name of EnumA.java and TestClass.java respectively.";
      this.question.option1correct = "true";
      this.question.option2text="3";
      this.question.option2answer="A public (or non-public) enum can be defined inside any class.";
      this.question.option2correct = "false";
      this.question.option3text="";
      this.question.option3answer="";
      this.question.option3correct = "false";
      this.question.option4text="";
      this.question.option4answer="";
      this.question.option4correct = "true";
      this.question.option5text="";
      this.question.option5answer="";
      this.question.option5correct = "true";
      this.question.sourceLink="https://enthuware.com";
      this.question.sourceDescription="trial EXAM 1Z0-819";
    }    

  }, 


  created() {

    console.log("API URL = "+this.$API_URL);

    this.service = new questionService(this.$API_URL);

  }

}

</script>
<style scoped>
  .c {
    text-align: center;
  }

 textarea {
  height: 350px;
}  
</style>