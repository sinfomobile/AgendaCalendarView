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
        Calendar maxDate = Calendar.getInstance();

        minDate.add(Calendar.MONTH, -2);
        minDate.set(Calendar.DAY_OF_MONTH, 1);
        maxDate.add(Calendar.YEAR, 1);

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
        Calendar startTime1 = Calendar.getInstance();
        Calendar endTime1 = Calendar.getInstance();
        endTime1.add(Calendar.MONTH, 1);
        BaseCalendarEvent event1 = new BaseCalendarEvent("Thibault travels in Iceland", "A wonderful journey!", "Iceland",
                ContextCompat.getColor(this, R.color.evento_aula_cor), startTime1, endTime1, true);
        eventList.add(event1);

        Calendar startTime2 = Calendar.getInstance();
        Calendar endTime2 = Calendar.getInstance();
        endTime2.add(Calendar.MONTH, 1);
        BaseCalendarEvent event2 = new BaseCalendarEvent("Visit to Dalvík", "A beautiful small town", "Dalvík",
                ContextCompat.getColor(this, R.color.evento_tarefa_cor), startTime2, endTime2, true);
        eventList.add(event2);

        Calendar startTime3 = Calendar.getInstance();
        Calendar endTime3 = Calendar.getInstance();
        endTime3.add(Calendar.MONTH, 1);
        BaseCalendarEvent event3 = new BaseCalendarEvent("Visit of Harpa", "", "Dalvík",
                ContextCompat.getColor(this, R.color.evento_prova_cor), startTime3, endTime3, true);
        eventList.add(event3);
    }

    // endregion
}