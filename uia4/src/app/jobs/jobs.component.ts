import {Component, OnInit, ViewChild} from '@angular/core';
// import { NgForm } from '@angular/forms';
import {FormGroup, FormBuilder, Validators} from '@angular/forms';
<<<<<<< HEAD
import {Http, Response} from '@angular/http';
import {DatatableComponent} from "@swimlane/ngx-datatable";
import {NgProgressService} from 'ngx-progressbar';

/**
 * An object used to get page information from the server
 */
export class Page {
  //The number of elements in the page
  size: number = 0;
  //The total number of elements
  totalElements: number = 0;
  //The total number of pages
  totalPages: number = 0;
  //The current page number
  pageNumber: number = 0;
}
=======
import {DatatableComponent} from "@swimlane/ngx-datatable";
>>>>>>> 862808cfdb7cb5358f9da9052883b5e9f414a055

@Component({
  selector: 'app-jobs',
  templateUrl: './jobs.component.html',
  styleUrls: ['./jobs.component.scss']
})
export class JobsComponent implements OnInit {

<<<<<<< HEAD
  rows: any;
  temp: any;

  page = new Page();
  cache: any = {};

  @ViewChild(DatatableComponent) table: DatatableComponent;

  constructor(private http: Http, private progressService: NgProgressService) {
    this.page.pageNumber = 0;
    this.page.size = 20;
=======
  columns = [
    {name: 'title'},
    {name: 'company_logo'},
    {name: 'location'},
    {name: 'type'}
  ]

  jobs = [
    {
      "id": "c62c9822-afb4-11e7-98ea-746cca247aa4",
      "created_at": "Fri Oct 13 01:23:08 UTC 2017",
      "title": "Front End Engineer",
      "location": "San Francisco",
      "type": "Full Time",
      "description": "<p>Imagine a world where every car where there’s a smartphone is safer, where massive amounts of data and insights makes cities safer and reduce insurance costs for everyone. Zendrive is executing rapidly on its mission of “making all ground transportation safer using data and analytics” and is focused on building products to support the evolution of transportation.<\/p>\n\n<p>We are looking for a Front End Software Engineer with 2-3 years experience programming with Modern Javascript Frameworks - like React, Angular or Ember. If you are passionate about writing code that may run on millions of devices, creating a rock solid technology that will save lives and you will not give up until the code is best in class, then Zendrive is the place for you.<\/p>\n\n<p><b>Responsibilities: <\/b><\/p>\n\n<ul>\n<li>Work closely with our product team to craft the next iteration of Zendrive’s front end products and applications<\/li>\n<li>Partner closely with the design in creating gorgeous, rich and modern web experiences<\/li>\n<li>Leverage your past successes in helping guide the future of our web properties<\/li>\n<li>Help us build an amazing continuous integration environment<\/li>\n<\/ul><p><b>Required Qualifications: <\/b><\/p>\n\n<ul>\n<li>Proactive and passionate attitude<\/li>\n<li>Good communication skills<\/li>\n<li>Obsessive about writing tests for modern code<\/li>\n<li>Basic knowledge of visual design tools; such as: Zeplin, Sketch or Photoshop.<\/li>\n<li>Strong analytical and numeric skills<\/li>\n<li>Minimum 2 years of Modern Javascript Frameworks including Single Page Applications and client/API architecture<\/li>\n<li>Minimum 2 years of application design and development including Responsive Web Applications with deep expertise in one of the following technologies – React, AngularJS, and Ember<\/li>\n<li>Minimum 2 years of experience with modern DevOps tools and workflows such as git, GitHub, Jira, Jenkins, or equivalents<\/li>\n<li>Minimum 2 years of Experience with agile development (Kanban, Scrum, etc.) <\/li>\n<\/ul><p><b>Preferred Qualifications:<\/b><\/p>\n\n<ul>\n<li>Minimum 1 years of API development using tools like Postman<\/li>\n<li>Minimum 6 months experience with Firebase<\/li>\n<li>Minimum 3 years of experience in JavaScript<\/li>\n<li>Minimum 1 years of experience with testing using TDD or BDD with 1 or more testing frameworks (e.g. Mocha/Chai, Jasmine)<\/li>\n<li>Minimum 1 years of experience in any of the following languages or technologies: Java, Groovy, Ruby, PHP, or Python<\/li>\n<li>Experience with Responsive Design frameworks, preferably with Bootstrap or Zurb Foundation<\/li>\n<li>Experience in developing high performing client-side applications<\/li>\n<li>Knowledge of CSS preprocessors (SASS, LESS or Stylus)<\/li>\n<li>Knowledge of what works in specific browsers. Responsive CSS (Mobile First, Bootstrap), Semantic HTML and HTML5, JS based languages such as Coffeescript and Typescript<\/li>\n<li>Strong preference for a design or engineering bachelor’s degree<\/li>\n<\/ul><p><b>Why join us?<\/b> \nWe thought you'd never ask! We offer all the usual stuff: competitive salary, medical/dental insurance, meals on site, gym and Spotify membership, top of the line equipment and compelling early stage equity. <\/p>\n\n<p>But the real perks are:  <\/p>\n\n<ul>\n<li>Challenging and fun work on meaningful problems - you'll never have a boring day at the office. <\/li>\n<li>World-class team of engineers and researchers who love solving tough problems. <\/li>\n<li>The opportunity to work on a problem that aims to improve the lives of everyone and will make a meaningful, positive impact on the world (while building a multi-billion dollar business). <\/li>\n<li>The chance to develop lasting business relationships by helping build our growing set of partners.<\/li>\n<\/ul>",
      "how_to_apply": "<p><a href=\"https://jobs.lever.co/zendrive/6071ae81-4589-4517-b2b2-faaffb0ffd85?lever-origin=applied&amp;lever-source%5B%5D=GitHub\">Apply Now<\/a><\/p>",
      "company": "Zendrive",
      "company_url": "https://www.zendrive.com/",
      "company_logo": "http://github-jobs.s3.amazonaws.com/599c88b6-afb4-11e7-8dff-d6f646f85f4a.png",
      "url": "http://jobs.github.com/positions/c62c9822-afb4-11e7-98ea-746cca247aa4"
    },
    {
      "id": "c0a925be-928b-11e7-99fb-1549ac463575",
      "created_at": "Fri Oct 06 16:37:17 UTC 2017",
      "title": "Software Engineer, Protein Sciences, Genentech Research",
      "location": "South San Francisco",
      "type": "Full Time",
      "description": "<p>Join a Team that Lives to Improve Lives<\/p>\n\n<p>People come to Genentech from across disciplines and across the world to solve our most challenging medical conditions. You’ll find inspiration in our passion for biotechnology, our purpose to positively impact the lives of millions of patients and our dedication to our people. Joining Genentech means being part of a tradition of inquiry that will change the world. It means embracing our failures as much as our successes. It requires a willingness to look beyond the edge of what's possible. And a focus on doing the day-to-day work that makes great science happen. <\/p>\n\n<p>The following opportunity exists in our South San Francisco, CA headquarters:<\/p>\n\n<p>Software Engineer, Protein Sciences, Genentech Research<\/p>\n\n<p>Description<\/p>\n\n<p>Protein Sciences is looking for a full-stack Software Engineer to participate in cross-product software architecture design and implementation. This candidate will be proactive and detail-oriented with strong technical skills, excellent written/verbal communication skills.<\/p>\n\n<ul>\n<li>Design, develop and implement programs and applications for integration of multiple data sources, manipulation and report<\/li>\n<li>Optimize existing tools and web application solutions<\/li>\n<li>Collaborate with scientists to understand workflows, collect requirements and design solutions<\/li>\n<li>Perform prototyping, application tests and code optimization to ensure product excellence<\/li>\n<li>Write clean, robust, maintainable, documented code using best engineering practices<\/li>\n<li>Practice agile methodology to ensure reliable and modular code<\/li>\n<\/ul><p>Who You Are<\/p>\n\n<ul>\n<li>Full-stack engineer with at least Bachelor degree in computer science or software engineering<\/li>\n<li>Experience working in Agile development environment<\/li>\n<li>5+ years of software programming experience in Java, Javascript, SQL, AngularJS, HTML, Python<\/li>\n<li>Database design: Oracle, MongoDB<\/li>\n<li>Proficiency in software lifecycle management tools; maintaining version control with Git and tracking with Atlassian tools (or similar)<\/li>\n<li>Excellent written and verbal communications skills<\/li>\n<\/ul><p>A Job with Benefits Beyond the Benefits<\/p>\n\n<p>A member of the Roche Group, Genentech has been at the forefront of the biotechnology industry for more than 40 years, using human genetic information to develop novel medicines for serious and life-threatening diseases. Genentech has multiple therapies on the market for cancer &amp; other serious illnesses. Please take this opportunity to learn about Genentech where we believe that our employees are our most important asset &amp; are dedicated to remaining a great place to work.<\/p>\n\n<p>The next step is yours. To apply today, click on the \"Apply online\" button.<\/p>\n\n<p>Genentech is an equal opportunity employer &amp; prohibits unlawful discrimination based on race, color, religion, gender, sexual orientation, gender identity/expression, national origin/ancestry, age, disability, marital &amp; veteran status. For more information about equal employment opportunity, visit our Genentech Careers page.<\/p>\n\n<p>Apply Link: <a href=\"https://www.gene.com/careers/detail/2734120811/Software-Engineer-Protein-Sciences-Genentech-Research\">https://www.gene.com/careers/detail/2734120811/Software-Engineer-Protein-Sciences-Genentech-Research<\/a><\/p>",
      "how_to_apply": "<p><a href=\"http://www.gene.com/careers/find-a-job/apply/2734120811?src=JB-11480\">http://www.gene.com/careers/find-a-job/apply/2734120811?src=JB-11480<\/a><\/p>",
      "company": "Genentech, Inc.",
      "company_url": "http://Genentech, Inc.",
      "company_logo": "http://github-jobs.s3.amazonaws.com/7a30c0ce-928b-11e7-8bbf-86f717d52dc5.PNG",
      "url": "http://jobs.github.com/positions/c0a925be-928b-11e7-99fb-1549ac463575"
    },
    {
      "id": "6d3927c6-a9fb-11e7-9d56-53be8d84ca84",
      "created_at": "Thu Oct 05 18:32:04 UTC 2017",
      "title": "Data Engineer",
      "location": "San Francisco",
      "type": "Full Time",
      "description": "<h1>About Quilt<\/h1>\n\n<p>Quilt's mission is to define the standard unit of enterprise data so that people and machines can learn faster. We believe that data should be managed like source code (with packaging, versioning, compilation, linting, and more). Our pilot customers are Fortune 500s who find that Quilt makes their data discoverable, reproducible, and auditable.<\/p>\n\n<p>You can learn more about Quilt in our <a href=\"https://www.youtube.com/watch?v=4S68CJN4N_0\">Y Combinator Demo Day presentation<\/a>, or on our <a href=\"https://quiltdata.com/\">website<\/a>.<\/p>\n\n<p>We're a small team of engineers who care deeply about code quality\nand user experience. We believe that data packages are the future of\ndata management, and we're actively defining the package standard through\n<a href=\"https://github.com/quiltdata/quilt-compiler\">our open source projects<\/a>.<\/p>\n\n<h1>About You<\/h1>\n\n<p>You have deep experience in several of the following domains:<\/p>\n\n<ul>\n<li>Distributed data<\/li>\n<li>Spark, Python, R<\/li>\n<li>Databases<\/li>\n<li>ETL<\/li>\n<li>Hadoop, Hive<\/li>\n<li>Parquet, ORC, Avro<\/li>\n<li>Docker, Kubernetes<\/li>\n<li>Machine learning (TensorFlow, PyTorch, scikit-learn)<\/li>\n<li>Apache Arrow<\/li>\n<li>Apache Airflow<\/li>\n<\/ul><h1>Cover letter<\/h1>\n\n<p>Please include at least three projects in data engineering that you've worked on. And tell us why you're interested in Quilt.<\/p>\n\n<h1>Minimum Qualifications<\/h1>\n\n<ul>\n<li>3 years of development experience<\/li>\n<li>BS in Computer Science or related field<\/li>\n<\/ul>",
      "how_to_apply": "<p>Send your resume and cover letter to <a href=\"mailto:contact@quiltdata.io\">contact@quiltdata.io<\/a>.<\/p>",
      "company": "Quilt - Docker for data",
      "company_url": "https://quiltdata.com",
      "company_logo": "http://github-jobs.s3.amazonaws.com/43deabbc-a9fb-11e7-9c13-f951a67525cd.png",
      "url": "http://jobs.github.com/positions/6d3927c6-a9fb-11e7-9d56-53be8d84ca84"
    },
    {
      "id": "d7a84152-a9f9-11e7-8c19-ce47d198cc32",
      "created_at": "Thu Oct 05 18:20:59 UTC 2017",
      "title": "Developer Advocate in Data Engineering",
      "location": "San Francisco",
      "type": "Full Time",
      "description": "<h1>About Quilt<\/h1>\n\n<p>Quilt's mission is to define the standard unit of enterprise data so that people and machines can learn faster. We believe that data should be managed like source code (with packaging, versioning, compilation, linting, and more). Our pilot customers are Fortune 500s who find that Quilt makes their data discoverable, reproducible, and auditable.<\/p>\n\n<p>You can learn more about Quilt in our <a href=\"https://www.youtube.com/watch?v=4S68CJN4N_0\">Y Combinator Demo Day presentation<\/a>, or on our <a href=\"https://quiltdata.com/\">website<\/a>.<\/p>\n\n<p>We're a small team of engineers who care deeply about code quality\nand user experience. We believe that data packages are the future of\ndata management, and we're actively defining the package standard through\n<a href=\"https://github.com/quiltdata/quilt-compiler\">our open source projects<\/a>.<\/p>\n\n<h1>About You<\/h1>\n\n<p>You have a successful track record of designing and implementing software systems at scale. You are a world-class communicator (in writing, on stage, and behind closed doors).<\/p>\n\n<p>You write beautiful technical articles. You can explain technical concepts to newcomers.<\/p>\n\n<p>You have deep experience in several of the following domains:<\/p>\n\n<ul>\n<li>Distributed data<\/li>\n<li>Spark, Python, R<\/li>\n<li>Databases<\/li>\n<li>ETL<\/li>\n<li>Hadoop, Hive<\/li>\n<li>Parquet, ORC, Avro<\/li>\n<li>Docker, Kubernetes<\/li>\n<li>Machine learning (TensorFlow, PyTorch, scikit-learn)<\/li>\n<li>Apache Arrow<\/li>\n<li>Apache Airflow<\/li>\n<\/ul><h1>Cover letter<\/h1>\n\n<p>Please include at least two videos of you speaking in public, two technical articles you've written, and two slide presentations you've created. And tell us why you're interested in Quilt.<\/p>\n\n<h1>Minimum Qualifications<\/h1>\n\n<ul>\n<li>5+ years of development experience<\/li>\n<li>BS in Computer Science or related field<\/li>\n<li>Demonstrated track record in public speaking and writing technical articles<\/li>\n<\/ul>",
      "how_to_apply": "<p>Send your resume and cover letter to <a href=\"mailto:contact@quiltdata.io\">contact@quiltdata.io<\/a>.<\/p>",
      "company": "Quilt - Docker for data",
      "company_url": "https://quiltdata.com",
      "company_logo": "http://github-jobs.s3.amazonaws.com/38d20658-a9f9-11e7-9112-fd1153fe4e66.png",
      "url": "http://jobs.github.com/positions/d7a84152-a9f9-11e7-8c19-ce47d198cc32"
    },
    {
      "id": "239f5730-a85b-11e7-9638-b8762f3ea213",
      "created_at": "Tue Oct 03 16:52:52 UTC 2017",
      "title": "Data Engineer",
      "location": "San Francisco",
      "type": "Full Time",
      "description": "<p>If you love clean data, we want to hear from you. We are looking for a Data Engineer superhero (or wizard behind the curtain)  who will keep our exec dashboard accurate and take the Analytics team’s data infrastructure to the next level. You will work with our data infrastructure, datasets and analytics tools that are used by the Executive, Product, Marketing, Sales, Sales Engineering, Finance and Customer Success teams every day--in short, your work will impact the whole company. This is a new role for HelloSign and you will play a critical role in shaping our Analytics foundation.<\/p>\n\n<p>You will contribute to a variety of projects that range from designing robust and fully automated ETL processes to building tools for improving company-wide productivity with data. You have a passion for designing, implementing and operating stable, scalable and efficient solutions to flow data from production systems into the data warehouse.\nWe know new analytics technologies are emerging every day and we are excited about the impact they will have – we hope you share our enthusiasm!\nResponsibilities:<\/p>\n\n<p>Interface with other technology teams to extract, transform, and load (ETL) data from a wide variety of in-house and 3rd party data sources\nEnsure we have data consistency on both production and analytical databases. You will own the integrity of our data from end to end and the company will make high impact decisions based on this data.\nArchitect and build data warehouse to provide timely data to a variety of third party applications (Salesforce, Marketo, etc)\nDesign and build tools that make our data pipelines and surfacing more reliable and easier to use\nWork closely with backend engineers to roll out new tools and features\nTriage, identify, and fix scaling challengesCollaborate with internal data customers to gather requirements\nHelp develop our data engineering function in areas of data architecture, business intuition &amp; insight or potentially big data.\nRequired Skills and Qualifications:<\/p>\n\n<p>3+ years experience with at least one relational database—MySQL, Postgres, Oracle or other.\nSQL Scripting and Data Warehousing experience using RDBMS\nExperience with large-scale data pipelines and ETL tooling.\nAbility to write efficient SQL queries\nSome coding experience. Our ETL process is in Ruby and our development environment is LAMP stack.\nBonus Points:<\/p>\n\n<p>Experience with EDA (Exploratory Data Analysis) and Data Visualization (we use Tableau)\nAmazon RedshiftExperience working with data using Python (pandas, numpy, scikit-learn, etc)\nBusiness Intelligence, Analytics or Finance experience\nExperience with any Hadoop type technology (Hive, Pig, Spark, etc)\nAbout Us:<\/p>\n\n<p>HelloSign is creating the next generation eSignature platform, with a focus on clean usability. We were recently voted #1 eSignature solution for Small Business and #1 software solution for Mid-Market business on G2 Crowd! We’re proud of these awards because they are voted on by real users and reflect our commitment to making life better for our customers. In fact, we have the highest customer satisfaction score out of all our competitors.<\/p>\n\n<p>In addition to our sleek end user product, developers love our API, clean, straightforward documentation and extensive SDKs. On average, full-featured integrations average less than 2.5 days of development--the fastest in the industry. And our API Engineers offer the best support you’ll never need. Our vision is to deliver Frictionless Agreements by making our users awesome.  <\/p>\n\n<p>Life at HelloSign:<\/p>\n\n<p>We are centrally located in downtown San Francisco in SOMA near Embarcadero. Currently at 60 employees, we are growing the company deliberately, with an eye towards maintaining a culture that values lifestyle, fun and continuous improvement. We were awarded the 2015 Hirepalooza Culture Award for Lifestyle and continue to maintain an overwhelmingly positive presence on Glassdoor and The Muse.<\/p>\n\n<p>We have raving fans who love what we make • We're user-focused and product-driven • We're always evolving with an eye towards improvement • Committed to building a product people want • We have a well-defined culture of fun, continual learning and collaboration • A supportive, familial atmosphere • An open, airy, creative space with communal dining and lounge spaces • We love dogs • Happy hour for unwinding and partaking in shenanigans • A fully stocked kitchen with drinks and snacks • And we'll never forget your birthday!<\/p>\n\n<p>HelloSign is an equal opportunity employer committed to hiring a diverse team of qualified individuals • HelloSign conducts background checks; pursuant to the San Francisco Fair Chance Ordinance, HelloSign will consider for employment qualified applicants with arrest and conviction records • HelloSign participates in E-Verify.<\/p>",
      "how_to_apply": "<p><a href=\"https://jobs.lever.co/hellosign/adfbc048-dfc2-4c85-b691-8e3f2ef545fa?lever-origin=applied&amp;lever-source%5B%5D=GitHub\">https://jobs.lever.co/hellosign/adfbc048-dfc2-4c85-b691-8e3f2ef545fa?lever-origin=applied&amp;lever-source%5B%5D=GitHub<\/a><\/p>",
      "company": "HelloSign",
      "company_url": "http://www.hellosign.com",
      "company_logo": "http://github-jobs.s3.amazonaws.com/21c9139c-a85b-11e7-94cc-327ba779223b.png",
      "url": "http://jobs.github.com/positions/239f5730-a85b-11e7-9638-b8762f3ea213"
    },
    {
      "id": "d3aebf16-a47e-11e7-9c65-3b9a172bd368",
      "created_at": "Thu Sep 28 18:57:31 UTC 2017",
      "title": "Mid Customer Success Engineer",
      "location": "San Francisco",
      "type": "Full Time",
      "description": "<h2>Job Description<\/h2>\n\n<p>As Customer Success Engineer you will be the face and voice of the company at conferences, meetings with potential partners and a point of reference for existing ones.<\/p>\n\n<p>Your goal will be to make our customers successfull integrating our APIs in their products, helping them writing code, sending examples, documentation, answering question they might have.<\/p>\n\n<p>You will be pro-active in writing our documentation and knowledge base and keeping up to date with new features and releases.<\/p>\n\n<h2>Skills &amp; Qualifications<\/h2>\n\n<ul>\n<li>Experience with multiple languages, JavaScript, Python, PHP, Objective-C<\/li>\n<li>Experience with implementing code following specs and documentation<\/li>\n<li>Bonus: Fluent in Chinese<\/li>\n<li>Bonus: Experience with working with Chinese Customers<\/li>\n<li>Bonus: 2+y of experience as customer success engineer<\/li>\n<\/ul>",
      "how_to_apply": "<p><a href=\"https://airtable.com/shrntut0UdEULJK2V\">https://airtable.com/shrntut0UdEULJK2V<\/a><\/p>",
      "company": "VIDY, Inc",
      "company_url": null,
      "company_logo": "http://github-jobs.s3.amazonaws.com/cc57a1ba-a47e-11e7-9d0e-7695f47a0eff.png",
      "url": "http://jobs.github.com/positions/d3aebf16-a47e-11e7-9c65-3b9a172bd368"
    },
    {
      "id": "9d43324c-a396-11e7-8f65-15cf1969f1a5",
      "created_at": "Wed Sep 27 15:14:53 UTC 2017",
      "title": "Senior Big Data Engineer",
      "location": "Cupertino, CA",
      "type": "Full Time",
      "description": "<p>DESCRIPTION: Changing the world is all in a day's work at Apple. If you love innovation, here's your chance to make a career of it. You'll work hard. But the job comes with more than a few perks.<\/p>\n\n<p>As a member of the Siri Data Engineering Operations team within the Siri organization, you will be faced with highly complex issues in a large scale, distributed system environment. In order to ensure a reliable and rewarding Siri experience, you will be empowered to develop and design new solutions heavily focused on system automation. We look for talented engineers in both the Operations and Development space to bring these unique solutions to production at a rapid pace. From an Operations perspective, this makes us the most successful personal assistant in the industry.<\/p>\n\n<p>Key Qualifications:\n•Knowledge of the Linux operation system (OS, networking, process level)\n•Understanding of Big Data technologies (Hadoop, Hbase, Spark, Kafka, Flume, Hive, etc)\n•Understanding of one or more object-oriented programming languages (Java, C++)\n•Fluent in at least one scripting language (Shell, Python, Ruby, etc.)\n•Strong verbal and written communication skills\n•Passionate about being a part of a tight-knit Operations team<\/p>\n\n<p>Description:\nThe Siri Data Engineering Operations team manages the platform for both ingestion and analytics of worldwide Siri events. To accomplish this, we build automation tools and services to prevent failures and page out individuals when there really is a problem, not just noise.<\/p>\n\n<p>Our engineers not only work closely with Operations, but also with the development and analytics engineers within Siri, as well as outside organizations. We build data pipelines for maximum efficiency, scalability and reliability to allow domain specific engineers to focus on their specialties. A successful candidate will have experience in being a Systems Administrator that has moved on to development and automation in their career.<\/p>\n\n<ul>\n<li><p>Operate Apple’s largest infrastructure supporting millions of Siri customers at double digit PB scale<\/p><\/li>\n<li><p>Troubleshoot complex issues across the entire stack<\/p><\/li>\n<li><p>Design and develop automation frameworks to handle both development and production events at scale<\/p><\/li>\n<li><p>Advise other teams (within and outside of Siri) on technical direction<\/p><\/li>\n<li><p>Make changes to our environment with the purpose of pushing Siri to the next level<\/p><\/li>\n<\/ul><p>Education:\nB.S. degree in Computer Science or 3+ years of building data pipelines experience or equivalent.<\/p>\n\n<p>Additional Requirements:\n(Nice to have)<\/p>\n\n<ul>\n<li>Java JVM performance tuning/optimizations<\/li>\n<li>Mesos compute platforms and job schedulers<\/li>\n<\/ul><p>Apple is an Equal Employment Opportunity Employer that is committed to inclusion and diversity. We also take affirmative action to offer employment and advancement opportunities to all applicants, including minorities, women, protected veterans, and individuals with disabilities.<\/p>",
      "how_to_apply": "<p>APPLY URL: <a href=\"https://ars2.equest.com/?response_id=7983283230f4fa7607cbf0a9c481bb8f\">https://ars2.equest.com/?response_id=7983283230f4fa7607cbf0a9c481bb8f<\/a>\nREQUISITION NUMBER: 99613876<\/p>",
      "company": "Apple Inc.",
      "company_url": "http://www.apple.com",
      "company_logo": "http://github-jobs.s3.amazonaws.com/9abda2e6-a396-11e7-8f15-e54e149b83fb.jpeg",
      "url": "http://jobs.github.com/positions/9d43324c-a396-11e7-8f65-15cf1969f1a5"
    },
    {
      "id": "84775832-9d98-11e7-965f-fcec69aa3804",
      "created_at": "Wed Sep 20 00:18:09 UTC 2017",
      "title": "Senior DevOps Engineer",
      "location": "San Francisco, CA",
      "type": "Full Time",
      "description": "<p>Orion Labs is dedicated to revolutionizing the way that people communicate. Our heads-up, wearable-enabled voice communication service helps people to stay connected across any distance and to collaborate more effectively.<\/p>\n\n<p>Orion Labs was founded by people who helped to shape the DevOps movement, and we are proud to continue in that tradition. Our products run on a high-availability, microservices-oriented platform, which supports a variety of voice-streaming clients and integrations.<\/p>\n\n<p>We're looking for an experienced engineer to join our team as Senior DevoOps Lead!<\/p>\n\n<p>About you:<\/p>\n\n<ul>\n<li>You have years of hard-won experience building and operating robust cloud-based web services infrastructure.<\/li>\n<li>You instrument and monitor everything you possibly can.<\/li>\n<li>You automate everything to keep from being woken up in the middle of the night.<\/li>\n<li>You love collaborating to design and implement elegant systems, but you're also ready to roll up your sleeves and get the job done.<\/li>\n<li>You know how to mentor and empower other engineers to succeed at their work.<\/li>\n<li>You've led an engineering team before, or you're a strong senior engineer ready to make the leap.\n-You are eager to learn!<\/li>\n<\/ul><p>About us:<\/p>\n\n<ul>\n<li>Our stack is implemented on AWS in a mix of Python and Go.<\/li>\n<li>We treat infrastructure as code, provisioning everything with Terraform and Chef, and we're starting to use Docker.<\/li>\n<li>We believe that Continuous Integration and Continuous Deployment mitigates risk, and you should too.<\/li>\n<li>We think of \"DevOps\" both as a kind of culture, and as a pattern for rapid change.<\/li>\n<li>We value confidence, humility, engagement, inclusiveness, consensus, and mutual trust.<\/li>\n<\/ul><p>We don't need you to be an expert in any particular technology -- we expect you to have a variety of tools in your kit, and the thoughtfulness to weigh trade-offs and apply each one wisely.<\/p>\n\n<p>We have a lot of interesting problems to solve as we continue to grow and scale out! As DevOps Lead, you'll help us design the future of the Orion Platform, and then enable your team to collaborate with others to build it.<\/p>\n\n<p>Come join us on this adventure! (And send us a link to your public Github profile, if you have one!)<\/p>\n\n<p>Orion is dedicated to building a diverse and inclusive team with a wide range of backgrounds and experiences. This means striving to hire a diverse set of humans and providing them with support, mentorship, and opportunities to grow. We firmly believe that hiring and supporting a diverse team is valuable endeavor in itself, and also allows us to do our best work and make better products. We don’t discriminate on the basis of race, religion, color, national origin, gender, sexual orientation, age, marital status, veteran status, ability status, or any other differences. \nAll are welcome here. <\/p>\n\n<p>Trans Can Work certified employer, 2017<\/p>",
      "how_to_apply": "<p>please apply here: <a href=\"http://grnh.se/08fcm71\">http://grnh.se/08fcm71<\/a><\/p>",
      "company": "Orion Labs",
      "company_url": "http://www.orionlabs.io",
      "company_logo": "http://github-jobs.s3.amazonaws.com/5055c408-9d98-11e7-8c81-4b91815a6d85.png",
      "url": "http://jobs.github.com/positions/84775832-9d98-11e7-965f-fcec69aa3804"
    },
    {
      "id": "c467dd52-9c82-11e7-806f-e04731f08dc0",
      "created_at": "Mon Sep 18 15:05:11 UTC 2017",
      "title": "Solution Architect",
      "location": "San Francisco",
      "type": "Full Time",
      "description": "<p><strong>ABOUT US<\/strong><\/p>\n\n<p>Contentful powers digital experiences for major brands around the globe. Its content infrastructure provides developers a powerful set of APIs to manage, integrate, and deliver content to any device or service -- be it mobile apps, IoT devices in the home, SaaS products, Super Bowl campaigns, smart car dashboards, digital signage, VR and AR experiences, or the next big platform yet to come. Companies like Spotify, Red Bull, Petsmart, Specialized, Twilio, and Urban Outfitters rely on Contentful to solve the complexities of content management in the modern multi-channel world.<\/p>\n\n<p>The demand for this next generation of tools is in the numbers: the company continues to double revenue year over year and has raised close to $20M from VC firms including Benchmark, Trinity, Balderton, and Point Nine.<\/p>\n\n<p>We're a fun team of over 130 people from 40 nations with offices in Berlin and San Francisco, looking for more amazing individuals to join our team!<\/p>\n\n<p><strong>ABOUT THE ROLE<\/strong><\/p>\n\n<p>Contentful is looking for our first San Francisco-based Solution Architect to provide consultation and advice to our prospects and customers, thereby helping them succeed in achieving their technical and business objective in using Contentful.  The ideal candidate is an experienced technologist with great interpersonal skills and an insatiable drive to learn.<\/p>\n\n<p>Contentful’s Solution Architects are principally outwards-facing. They have credibility with technologists and developers of all levels and are effective liaisons with stakeholders from marketing, the content team, and business development.  As a Solution Architect at Contentful, you’ll have the opportunity to work with teams around the world from visionary brands and innovative digital agencies. Your technical guidance, strategic advice and product expertise will help them make the right decisions and achieve success with their projects. You’ll travel to visit prospects and customers, attend conferences, and deliver training sessions; a reasonable expectation would be travel roughly 25% of the time.<\/p>\n\n<p>The insights gained during your work with prospects and customers will enable you to provide valuable input to our Product Management and Engineering departments. Your first-hand accounts of how customers interact with our product, tempered by your experience, will provide crucial real world context for our teams building efforts.<\/p>\n\n<p>Your domain expertise will also enable you to provide industry thought leadership on the topics of content structuring, microservices architectures, and content deployment and delivery best-practices. You’ll share this knowledge via writing whitepapers, leading training workshops, or speaking at conferences.<\/p>\n\n<p><strong>RESPONSIBILITIES<\/strong><\/p>\n\n<ul>\n<li>Working closely with the Sales team and Customer Success Managers, help new customers successfully implement and launch their projects with Contentful<\/li>\n<li>Provide ongoing consultation and advice to customers about Contentful’s features and architectural design best-practices, thereby aiding their projects’ long-term success<\/li>\n<li>Develop training materials and lead workshops on using Contentful to achieve technical and business objectives<\/li>\n<li>Provide thought leadership on API-first &amp; headless as the right approach for the management and delivery of content in today's multi-channel world<\/li>\n<li>Collaborate with Sales Engineers to support our sales team in helping prospects understand what’s possible with Contentful and how it compares to older legacy technologies<\/li>\n<li>Synthesize and communicate prospect and customer product feedback to our Product Management, Engineering, and Marketing teams<\/li>\n<\/ul><p><strong>YOUR PROFILE<\/strong><\/p>\n\n<ul>\n<li>A Bachelor’s or Master’s degree in Computer Science, MIS, or a comparable field, or equivalent experience<\/li>\n<li>2+ years in Lead Engineering or Consulting position/s for a developer-focused/technical SaaS company<\/li>\n<li>Hands-on experience with programming in several of the following languages: Java, PHP, .NET, Ruby, Node.js, and Python<\/li>\n<li>Proven experience with current web technologies (JS frameworks, static site generators, REST APIs, CMSs, CDNs, etc.) and mobile development (iOS, Android)<\/li>\n<li>Recent professional web development experience is preferred<\/li>\n<li>Excellent English communication skills, both verbal and written, and confidence conveying knowledge to a highly experienced technical audience<\/li>\n<li>Authorization to work in the United States<\/li>\n<\/ul><p><strong>BENEFITS<\/strong><\/p>\n\n<ul>\n<li>Join an innovative tech company as we help drive the evolution of digital experiences to become ever-more ubiquitous and interactive. Be a part of helping companies build modern architectures for mission-critical applications.<\/li>\n<li>Shape the future of Contentful: help us establish, scale, and improve our team's processes.<\/li>\n<li>Generous education budget complete with extra days off to be spent on your professional and self-development.<\/li>\n<li>Be set up for success, equipped with the latest and greatest hardware.<\/li>\n<li>Hang-out in one of our many shared spaces, playing games with colleagues or enjoying a full range of events, including workshops, on-site meetups, guest speakers, and fun events for the company and each team. Did we mention an annual off-site?<\/li>\n<li>As much artisan coffee as you can handle.<\/li>\n<li>Sharpen your PlayStation, ping pong, and kicker/fußball skills during breaks in the day.<\/li>\n<li>Brush up your language skills! Our team speaks more than 20 languages, and we offer free German classes.<\/li>\n<li>Take a break and pat a pup, we are a dog-friendly office.<\/li>\n<li>We fully support your move to Berlin with a relocation budget and visa assistance. We'll help you settle into your exciting new city.<\/li>\n<li>Plus, Contentful socks, oh yeah!<\/li>\n<\/ul><p><em>“Variety is the spice of life”<\/em> — and a celebrated component of our culture. At Contentful, we strive to create an inclusive environment that empowers our employees. We believe that our products and services benefit from our diverse backgrounds and experiences and are proud to be an equal opportunity employer: all qualified applicants are considered for positions regardless of race, ethnic origin, gender, age, religion or belief, marital status, gender identification, sexual orientation, or disability. We look forward to your application!<\/p>",
      "how_to_apply": "<p>Apply via link: <a href=\"http://grnh.se/a1etfu1\">http://grnh.se/a1etfu1<\/a><\/p>",
      "company": "Contentful GmbH",
      "company_url": "https://www.contentful.com",
      "company_logo": "http://github-jobs.s3.amazonaws.com/c1e0cbac-9c82-11e7-9606-8ecbc31d5485.jpg",
      "url": "http://jobs.github.com/positions/c467dd52-9c82-11e7-806f-e04731f08dc0"
    }
  ];
  temp : any;

  @ViewChild(DatatableComponent) table: DatatableComponent;

  constructor(private formBuilder: FormBuilder) {
    this.temp = this.jobs;
>>>>>>> 862808cfdb7cb5358f9da9052883b5e9f414a055
  }

  ngOnInit() {
    this.getJobs();
    this.setPage({ offset: 0 });
  }

  search() {
<<<<<<< HEAD
    console.log('search jobs');
  }

  getJobs() {
    this.progressService.start();
    this.http.get('/jobs').subscribe(res => {
      var jobs = res.json();

      this.rows = jobs;
      this.temp = this.rows;
      this.progressService.done();
    });
  }

  updateFilter(event : any) {
    const val = event.target.value.toLowerCase();

    this.progressService.start();

    // filter our data
    const temp = this.temp.filter(function (d : any) {
      return d.title.toLowerCase().indexOf(val) !== -1 ||
        d.company.toLowerCase().indexOf(val) !== -1 ||
        d.location.toLowerCase().indexOf(val) !== -1 ||
        !val;
    });

    this.rows = temp;
    this.table.offset = 0;
    this.progressService.done();
  }

  /**
   * Populate the table with new data based on the page number
   * @param page The page to select
   */
  setPage(pageInfo : any){
    this.page.pageNumber = pageInfo.offset;
    // this.serverResultsService.getResults(this.page).subscribe(pagedData => {
    //   this.page = pagedData.page;
    //   this.rows = pagedData.data;
    // });
=======
    console.log('search');
  }

  updateFilter(event :any) {
    const val = event.target.value.toLowerCase();

    // filter our data
    const temp = this.temp.filter(function(query : any) {
      return query.title.toLowerCase().indexOf(val) !== -1 ||
        query.company.toLowerCase().indexOf(val) !== -1 ||
        query.location.toLowerCase().indexOf(val) !== -1 ||
        !val;
    });

    // update the rows
    this.jobs = temp;

    // Whenever the filter changes, always go back to the first page
    this.table.offset = 0;
    console.log(val);
>>>>>>> 862808cfdb7cb5358f9da9052883b5e9f414a055
  }
}
