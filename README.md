# Crawlable CV

## Abstract

Being asked for your CV on [LinkedIn](https://linkedin.com) or similar platforms can be a great opportunity you wouldn't want to miss out. 
It could also turn out to be one of those CV-Crawlers or even worse.<br>
This Project aims to create dynamic CVs with a fine grained privacy policy. Proving your work experience without disclosing your full name, address, phone number, customer names, etc.<br>
Details can be revealed on demand during further contact, an updated document must be downloaded.

## So much to do, so little time - //TODO

- [ ] Get Pandoc to run
- [ ] TexTemplate
- [ ] Basic WebService to create rendered documents (preferably PDF)
- [ ] Roles/Rights model + Access control
- [ ] Client app to update privileges
    - [ ] iOS
    - [ ] Android
    - [ ] Web
- [ ] Professional network connection

## Getting started

- Make sure [Docker](https://www.docker.com/get-started/) is running
- Build project (bootJar) / Download binary
- Run container (Dockerfile run-config)

## Dependencies

- [Docker](https://www.docker.com/)
- [Pandoc - Docker container](https://hub.docker.com/r/pandoc/latex)
- [Spring](https://spring.io/)
  - [spring initializr](https://start.spring.io/)
  - Spring Security
- [Lombok](https://projectlombok.org/)