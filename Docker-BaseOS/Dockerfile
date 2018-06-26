FROM ubuntu:16.04

# Install Tesseract dependencies and the language support
RUN apt-get update && apt-get install -y tesseract-ocr 
RUN apt-get install -y tesseract-ocr-eng 
RUN apt-get install -y tesseract-ocr-afr
RUN apt-get install -y tesseract-ocr-ara
RUN apt-get install -y tesseract-ocr-aze
RUN apt-get install -y tesseract-ocr-bel
RUN apt-get install -y tesseract-ocr-ben
RUN apt-get install -y tesseract-ocr-bul
RUN apt-get install -y tesseract-ocr-cat
RUN apt-get install -y tesseract-ocr-ces
RUN apt-get install -y tesseract-ocr-chi-sim
RUN apt-get install -y tesseract-ocr-chi-tra
RUN apt-get install -y tesseract-ocr-chr
RUN apt-get install -y tesseract-ocr-dan
RUN apt-get install -y tesseract-ocr-deu
RUN apt-get install -y tesseract-ocr-deu-frak
RUN apt-get install -y tesseract-ocr-dev
RUN apt-get install -y tesseract-ocr-ell
RUN apt-get install -y tesseract-ocr-enm
RUN apt-get install -y tesseract-ocr-epo
RUN apt-get install -y tesseract-ocr-equ
RUN apt-get install -y tesseract-ocr-est
RUN apt-get install -y tesseract-ocr-eus
RUN apt-get install -y tesseract-ocr-fin
RUN apt-get install -y tesseract-ocr-fra
RUN apt-get install -y tesseract-ocr-frk
RUN apt-get install -y tesseract-ocr-frm
RUN apt-get install -y tesseract-ocr-glg
RUN apt-get install -y tesseract-ocr-grc
RUN apt-get install -y tesseract-ocr-heb
RUN apt-get install -y tesseract-ocr-hin
RUN apt-get install -y tesseract-ocr-hrv
RUN apt-get install -y tesseract-ocr-hun
RUN apt-get install -y tesseract-ocr-ind
RUN apt-get install -y tesseract-ocr-isl
RUN apt-get install -y tesseract-ocr-ita
RUN apt-get install -y tesseract-ocr-ita-old
RUN apt-get install -y tesseract-ocr-jpn
RUN apt-get install -y tesseract-ocr-kan
RUN apt-get install -y tesseract-ocr-kor
RUN apt-get install -y tesseract-ocr-lav
RUN apt-get install -y tesseract-ocr-lit
RUN apt-get install -y tesseract-ocr-mal
RUN apt-get install -y tesseract-ocr-mkd
RUN apt-get install -y tesseract-ocr-mlt
RUN apt-get install -y tesseract-ocr-msa
RUN apt-get install -y tesseract-ocr-nld
RUN apt-get install -y tesseract-ocr-nor
RUN apt-get install -y tesseract-ocr-osd
RUN apt-get install -y tesseract-ocr-pol
RUN apt-get install -y tesseract-ocr-por
RUN apt-get install -y tesseract-ocr-ron
RUN apt-get install -y tesseract-ocr-rus
RUN apt-get install -y tesseract-ocr-slk
RUN apt-get install -y tesseract-ocr-slk-frak
RUN apt-get install -y tesseract-ocr-slv
RUN apt-get install -y tesseract-ocr-spa
RUN apt-get install -y tesseract-ocr-spa-old
RUN apt-get install -y tesseract-ocr-sqi
RUN apt-get install -y tesseract-ocr-srp
RUN apt-get install -y tesseract-ocr-swa
RUN apt-get install -y tesseract-ocr-swe
RUN apt-get install -y tesseract-ocr-tam
RUN apt-get install -y tesseract-ocr-tel
RUN apt-get install -y tesseract-ocr-tgl
RUN apt-get install -y tesseract-ocr-tha
RUN apt-get install -y tesseract-ocr-tur
RUN apt-get install -y tesseract-ocr-ukr
RUN apt-get install -y tesseract-ocr-vie

# Install System components needed
RUN apt-get install -y curl && apt-get install -y wget && apt-get install -y ghostscript
# Install Java
RUN echo debconf shared/accepted-oracle-license-v1-1 select true | debconf-set-selections && echo debconf shared/accepted-oracle-license-v1-1 seen true | debconf-set-selections && apt-get install -y software-properties-common && add-apt-repository ppa:webupd8team/java -y && apt-get update && apt-get install -y oracle-java8-installer && apt-get install -y oracle-java8-set-default

ARG UID=1000
ARG GID=1000

ENV WORKSPACE_LOC /opt/workspace
ENV WORKSPACE_LIB ${WORKSPACE_LOC}/lib 

# Setup NiFi user
RUN groupadd -g ${GID} james || groupmod -n james `getent group ${GID} | cut -d: -f1` \
    && useradd --shell /bin/bash -u ${UID} -g ${GID} -m james \
    && mkdir -p ${WORKSPACE_LOC} \
    && mkdir -p ${WORKSPACE_LOC}\data \
    && mkdir -p ${WORKSPACE_LIB} \ 
    && chown -R james:james ${WORKSPACE_LOC} \
    && apt-get update \
    && apt-get install -y jq xmlstarlet

VOLUME     ${WORKSPACE_LOC}\data \

USER james

WORKDIR ${WORKSPACE_LOC}

CMD ["/bin/bash"]