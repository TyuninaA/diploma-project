package com.example.dod_app;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class Info extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_info, container, false);

        Button btnContacts = view.findViewById(R.id.btnContacts);
        btnContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showContactsDialog(getContext());
            }
        });

        Button btnAbout = view.findViewById(R.id.btnAbout);
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAboutDialog(getContext());
            }
        });

        Button btnInt = view.findViewById(R.id.btnInt);
        btnInt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInternationalOfficeDialog(getContext());
            }
        });

        Button btnJob = view.findViewById(R.id.btnJob);
        btnJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showJobOpeningsDialog(getContext());
            }
        });

        Button btnFac = view.findViewById(R.id.btnFac);
        btnFac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFacultiesInformation(getContext());
            }
        });

        Button btnProf = view.findViewById(R.id.btnProf);
        btnProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProfessorsInformation(getContext());
            }
        });

        return view;
    }

    private void showContactsDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Contacts");

        StringBuilder message = new StringBuilder();
        message.append("Call or write us:\n");
        message.append("Phone: +7 727 355-05-51\n");
        message.append("WhatsApp: +7 700 355-05-51\n");
        message.append("E-Mail: info@dku.kz\n\n");
        message.append("Main Building \"A\" Address:\n");
        message.append("Pushkin Street, 111\n");
        message.append("Almaty, 050010\n");
        message.append("Kazakhstan\n\n");
        message.append("Second Building \"B\" Address:\n");
        message.append("Nazarbayev Avenue, 173\n");
        message.append("Almaty, 050010\n");
        message.append("Kazakhstan\n\n");
        message.append("Ask about admission to DKU:\n");
        message.append("+7 727 355 05 63\n");
        message.append("admission@dku.kz\n\n");
        message.append("Ask about research collaboration:\n");
        message.append("+7 727 355 05 51 (ext. 238)\n");
        message.append("beimenbetov@dku.kz\n");
        message.append("tyulyubayeva@dku.kz\n\n");
        message.append("Ask about collaboration:\n");
        message.append("+7 727 355-05-51 (ext. 327)\n");
        message.append("marketing@dku.kz");

        builder.setMessage(message.toString());

        builder.setPositiveButton("OK", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showAboutDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Mission, Principles, and Strategy");

        String aboutMessage = "<b>Our Mission:</b><br/><br/>" +
                "We are an international network university inspired by the idea of Humboldt University. In Central Asia, we combine science, society and culture into a unique system of education, research and transfer. We inspire students, teachers and scientists to contribute to the social and economic prosperity, as well as the sustainable development of Kazakhstan and Central Asia. We are committed to our national heritage and are an independent academic organization. Based on the Kazakh-German Intergovernmental Agreement, we are the flagship project of scientific relations between the two countries.<br/><br/>" + // Replace with actual mission text
                "<b>Principles of DKU:</b><br/><br/>" +
                "<b>1. Quality and Autonomy:</b><br/>" +
                "The structure of the university is based on the German principles of university autonomy. However, they are not an end in themselves. Autonomy is provided so that the quality of science at DKU can develop in the best possible way. The autonomy of the university and the highest standards of scientific quality are the basis of teaching, research and transfer at the university: We are a learning organization and we are constantly improving the quality of our scientific activities. Our quality assurance, accreditation and evaluation system guarantees this. We also place high demands on our services. Our quality standards are based on the best practices of the best universities in Europe and Germany.<br/><br/>" +
                "<b>2. Kazakhstan and Germany:</b><br/>" +
                "The University promotes the establishment of contacts between German and Kazakh culture and science. The bi-national heritage characterizes the university's activities. The governing bodies of DKU regularly include citizens of Kazakhstan and Germany. We serve as a bridge for scientific and cultural exchange between Germany and Kazakhstan: Learning foreign languages and acquiring intercultural competencies is the central element of our university. We pay special attention to German, English, Russian and Kazakh languages. The connection with the German language and culture plays a special role in this regard. We promote the international mobility of our students and staff, in particular mobility between Kazakhstan and Germany. We are supported by a consortium of German universities. We cooperate with German cultural institutions in Kazakhstan.<br/><br/>"+
                "<b>3. Responsibility to society:</b><br/>" +
                "Without science, the social challenges of the 21st century cannot be solved on a large or small scale, which are brought about by climate change, digitalization and globalization. DKU is responsible for the sustainable development of the world and in this regard: Education, research and transfer at DKU focus on issues that relate to social, economic and technological change in Kazakhstan and Central Asia. At DKU, scientists from different disciplines teach and conduct collaborative research on an interdisciplinary basis to contribute to solving the problems and aspects of social, economic and technological change in Kazakhstan and Central Asia. The guiding principles of DKU's activities are the Sustainable Development Goals of the United Nations Agenda.<br/><br/>"+
                "<b> 4. University culture:</b><br/>" +
                "For students, researchers, teachers, administrative and managerial staff, joint work at the university is regulated not only by the Legislation of the Republic of Kazakhstan, but also by internal acts of DKU, regulations, rules and instructions, but is also based on a common understanding of values that are mandatory for all. We are developing an open university culture: Appreciation, respect, tolerance, honesty and openness define our cooperation. To achieve our goals, we take on overall responsibility for the development of our organizational and management structures. Everyone who teaches, researches and studies at the university must follow the rules of good scientific practice. We encourage strengths and talents. We ensure equal opportunities and non-discrimination, and pay attention to the health and safety of our students and staff. We are a responsible employer. We make sure that there is no corruption at the university. Together, we contribute to making the university campus an attractive place for learning, research, transfer and dialogue with society.</b><br/><br/>" +
        "<b> 5. Study and teaching:</b><br/>" +
                "Studying at DKU is a high—quality academic education according to German standards, the curricula of which are developed in cooperation with German universities. Visiting professors and teachers from Germany are involved in teaching. We provide our students with a high level of professional qualifications in accordance with German standards. Our educational program prepares students for professional success: We instill intercultural competencies and foreign language skills to work in international professional fields. We offer a double degree with German partner universities in all our bachelor's degree programs, and in the future in Master's degree programs. We promote personal development and teach independent and responsible behavior, as well as analytical thinking. The prospects for sustainable development are an integral part of the program. Our students learn to think and act based on an interdisciplinary approach. We use innovative teaching methods. We regularly improve our teaching methods and develop new digital learning formats.</b><br/><br/>" +
                "<b> 6. The Triangle of Knowledge: education, research and innovation:</b><br/>" +
                "Education, research and transfer are the three equal pillars of DKU, which are interconnected. Research, education and innovation form a triangle of knowledge at DKU. DKU initiates and organizes platforms and networks for the purpose of introducing and integrating Kazakh science into the German educational and research field. The network interaction is based on strategic partnership with Kazakh and German universities and research organizations. To promote young scientists, DKU cooperates with leading German universities that are strong in the field of research.\n" + "DKU contributes to the development of society, as well as participates in dialogue and knowledge exchange with stakeholders in business, society and politics, and collaborates on projects with industry.</b>";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.setMessage(Html.fromHtml(aboutMessage, Html.FROM_HTML_MODE_LEGACY));
        }

        builder.setPositiveButton("OK", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showInternationalOfficeDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("International Office");

        String intOfficeMessage = "Our university offers students the German standard of education, and we closely collaborate with partner universities in Germany, as well as universities in neighboring and distant countries. We also work with scientific, social, and cultural organizations and institutions.\n\n" +
                "Academic exchange programs at DKU are a crucial factor in the university's development and the high competitiveness of our students.\n\n" +
                "By reaching out to us, you will receive information about educational programs, grants, and opportunities for studying and internships in Germany. Our goal is not only to advise you on organizing your stay abroad but also to provide effective practical assistance at every stage.\n\n" +
                "Contacts:\n" +
                "Phone: +7 727 355-05-51 (ext. 242)\n" +
                "Elena Asmus\n" +
                "int_office@dku.kz\n\n" +
                "Aizhan Uazieva\n" +
                "visa@dku.kz";


        builder.setMessage(intOfficeMessage);
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showJobOpeningsDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Job Openings");

        StringBuilder message = new StringBuilder();
        message.append("Explore current job openings at DKU.\n\n");
        message.append("\nYou can follow the latest job openings on our Telegram channel: ");
        message.append("<a href='https://t.me/dkukarriere'>https://t.me/dkukarriere</a>");

        TextView textView = new TextView(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.setText(Html.fromHtml(message.toString(), Html.FROM_HTML_MODE_LEGACY));
        } else {
            textView.setText(Html.fromHtml(message.toString()));
        }

        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setClickable(true);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        builder.setView(textView);

        builder.setPositiveButton("OK", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showFacultiesInformation(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Faculties Information");

        String facultiesMessage = "At DKU, we have several faculties offering diverse programs and courses to foster academic growth and excellence.\n\n" +
                "1. Faculty of Engineering and Information Technology\n" +
                "2. Faculty of Economics and Entrepreneurship\n" +
                "3. Faculty of World Politics\n\n" +
                "Each faculty is committed to providing high-quality education and research opportunities to our students.";

        builder.setMessage(facultiesMessage);

        builder.setPositiveButton("OK", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showProfessorsInformation(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Professors Information");

        String professorsMessage = "Meet some of our distinguished professors:\n\n" +
                "1. Dadaeva Irina Georgievna\n" +
                "   - Department: Mathematical foundations of computer science\n" +
                "   - Email: dadaeva@dku.kz\n\n" +
                "2. Kiseleva Olga Vladimirovna\n" +
                "   - Department: PhD in Information Systems\n\n" +
                "3. Savelieva Elena Anatolyevna\n" +
                "   - Department: Computer networks\n" +
                "   - Email: savelyeva@dku.kz\n\n" +
                "4. Temiralieva Gulnara Tursynkhanova\n" +
                "   - Department: Procedural programming\n" +
                "   - Email: temiraliyeva@dku.kz\n\n" +
                "5. Bakirova Gulnaz Sailauovna\n" +
                "   - Department: Information and communication technologies\n" +
                "   - Email: bakirova@dku.kz\n\n" +
                "Feel free to reach out to them for academic inquiries and research collaborations.";

        builder.setMessage(professorsMessage);

        builder.setPositiveButton("OK", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

