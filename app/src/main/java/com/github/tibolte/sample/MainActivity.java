package com.github.tibolte.sample;

import com.github.tibolte.agendacalendarview.AgendaCalendarView;
import com.github.tibolte.agendacalendarview.CalendarPickerController;
import com.github.tibolte.agendacalendarview.models.BaseCalendarEvent;
import com.github.tibolte.agendacalendarview.models.CalendarEvent;
import com.github.tibolte.agendacalendarview.models.DayItem;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements CalendarPickerController {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Bind(R.id.activity_toolbar)
    Toolbar mToolbar;
    @Bind(R.id.agenda_calendar_view)
    AgendaCalendarView mAgendaCalendarView;

    // region Lifecycle methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        // minimum and maximum date of our calendar
        // 2 month behind, one year ahead, example: March 2015 <-> May 2015 <-> May 2016
        Calendar minDate = Calendar.getInstance();
        minDate.add(Calendar.MONTH, -1);

        Calendar maxDate = Calendar.getInstance();

//        minDate.add(Calendar.MONTH, 0);
//        minDate.set(Calendar.DAY_OF_MONTH, 1);

        maxDate.add(Calendar.MONTH, 1);

        List<CalendarEvent> eventList = new ArrayList<>();
        mockList(eventList);

        mAgendaCalendarView.init(eventList, minDate, maxDate, Locale.getDefault(), this);
        mAgendaCalendarView.addEventRenderer(new DrawableEventRenderer());
    }

    // endregion

    // region Interface - CalendarPickerController

    @Override
    public void onDaySelected(DayItem dayItem) {
        Log.d(LOG_TAG, String.format("Selected day: %s", dayItem));
    }

    @Override
    public void onEventSelected(CalendarEvent event) {
        Log.d(LOG_TAG, String.format("Selected event: %s", event));
    }

    @Override
    public void onScrollToDate(Calendar calendar) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()));
        }
    }

    // endregion

    // region Private Methods

    private void mockList(List<CalendarEvent> eventList) {


//        Calendar startTime2 = Calendar.getInstance();
//        Calendar endTime2 = Calendar.getInstance();
//        endTime2.add(Calendar.MONTH, 1);
//        BaseCalendarEvent event2 = new BaseCalendarEvent("Visit to Dalvík", "A beautiful small town", "Dalvík",
//                ContextCompat.getColor(this, R.color.evento_tarefa_cor), startTime2, endTime2, true);
//        eventList.add(event2);
//
//        Calendar startTime3 = Calendar.getInstance();
//        Calendar endTime3 = Calendar.getInstance();
//        endTime3.add(Calendar.MONTH, 1);
//        BaseCalendarEvent event3 = new BaseCalendarEvent("Visit of Harpa", "", "Dalvík",
//                ContextCompat.getColor(this, R.color.evento_prova_cor), startTime3, endTime3, true);
//        eventList.add(event3);
//
//        Calendar startTime4 = Calendar.getInstance();
//        startTime4.add(Calendar.MONTH, -1);
//        Calendar endTime4 = Calendar.getInstance();
//        endTime4.add(Calendar.MONTH, 0);
//        BaseCalendarEvent event4 = new BaseCalendarEvent("Visit of Harpa", "", "Dalvík",
//                ContextCompat.getColor(this, R.color.evento_prova_cor), startTime4, endTime4, true);
//
//        eventList.add(event4);

        Calendar hoje = Calendar.getInstance();
        Calendar fim = Calendar.getInstance();

        BaseCalendarEvent event5 = new BaseCalendarEvent("Meu Teste", "", "Meu Teste",
                ContextCompat.getColor(this, R.color.corBordaSelecao), hoje, fim, true);

        eventList.add(event5);

        Calendar amanha = Calendar.getInstance();
        amanha.add(Calendar.DAY_OF_MONTH, 1);
        Calendar depois = Calendar.getInstance();
        depois.add(Calendar.DAY_OF_MONTH, 1);

        BaseCalendarEvent event6 = new BaseCalendarEvent("Meu Teste 2", "", "Meu Teste 2",
                ContextCompat.getColor(this, R.color.corBordaSelecao), amanha, depois, true);

        eventList.add(event6);

        Calendar ontem = Calendar.getInstance();
        ontem.add(Calendar.MONTH, -1);
        Calendar ontemDepois = Calendar.getInstance();
        ontemDepois.add(Calendar.DAY_OF_MONTH, 1);

        BaseCalendarEvent event7 = new BaseCalendarEvent("Meu Teste 3", "", "Meu Teste 3",
                ContextCompat.getColor(this, R.color.corBordaSelecao), ontem, ontemDepois, true);

        eventList.add(event7);



    }

    // endregion
}