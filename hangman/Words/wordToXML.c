#include <stdio.h>
#include <stdlib.h>




int main ()
 {
    FILE *fw;
    FILE *fr;
    char fileread[] = "words.txt";
    char filewrite[] = "wordsXML.txt";
    if ((fr=fopen(fileread, "r"))==NULL) {
     printf ("Nie mogê otworzyæ pliku do wczytu!\n");
     exit(1);
    }
    if ((fw=fopen(filewrite, "w"))==NULL) {
    printf ("Nie mogê otworzyæ pliku do zapisu!\n");
    exit(1);
    }

    while (!feof(fr)) {
        char line[100];
        fscanf(fr, "%s", line);
        fprintf(fw, "<item>%s</item>\n", line);
    }


    fclose (fr);
    fclose (fw);
    return 0;
 }
