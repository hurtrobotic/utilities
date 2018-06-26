FROM hurtrobotic/ubuntu-ocr
ENV WORKSPACE_LOC /opt/workspace
ENV TESSDATA_PREFIX /usr/share/tesseract-ocr/tessdata
ADD maven/tess4J-0.0.1-SNAPSHOT.jar /opt/workspace/lib/myapp.jar
RUN sh -c 'touch /opt/workspace/lib/myapp.jar'

USER james

WORKDIR ${WORKSPACE_LOC}

CMD ["/bin/bash"]