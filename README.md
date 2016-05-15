# GestionHospital - Obsolete - Migrated to private repo - BitBucket
Project for the Hospital of Vinaròs, applied lightly MVC, Model is no longer interact with the view. Data Structure prepared for multithread (Not Thread-safe yet and not sure if it's necessary).
Developed with JAVA SE 8 and Swing framework.

__Formed by 6 packets:

-> Controller (In use)

-> ActionListeners (Classes that extends JFrame and are support for the VIEW, 
implement a few ActionListeners and some graphical windows to input text)

-> Media (save all resource media)

-> Model (Logic application) with:

    -estructurasED (keeps the data in a Hashmap)
    -paciente (Class to save the patient information)
    -principal (Just the main method)

->test (Tested a few classes with JUnit 4 Framework)

->vista (Save and interacts with the view Swing)

# External Libraries
__Links:


-> GSON from Google to parse GestionPaciente.class  
https://github.com/google/gson


-> JExcelApi to perform a writable xls Excel sheet with all the data
http://jexcelapi.sourceforge.net


-> JCalendar to display a tiny GUI calendar to pick dates
http://toedter.com/jcalendar/




# LICENSE:
En la presente licencia de ‘Gestión Hospitalaria’ desarrollada íntegramente por Albert Antoni Jiménez Fuentes, se permite el uso a Pablo Serra Bel y superiores, siempre que se respete el uso como tal del software a fines personales. Cualquier modificación, redistribución y/o venta del mismo suponen una anulación de los fines destinados con consecuencias legales.
