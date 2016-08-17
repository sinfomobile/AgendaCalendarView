package com.github.tibolte.agendacalendarview.agenda;

import com.github.tibolte.agendacalendarview.CalendarManager;
import com.github.tibolte.agendacalendarview.R;
import com.github.tibolte.agendacalendarview.utils.DateHelper;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Header view for the StickyHeaderListView of the agenda view
 */
public class AgendaHeaderView extends LinearLayout {

    public static AgendaHeaderView inflate(ViewGroup parent) {
        return (AgendaHeaderView) LayoutInflater.from(parent.getContext()).inflate(R.layout.view_agenda_header, parent, false);
    }

    // region Constructors

    public AgendaHeaderView(Context context) {
        super(context);
    }

    public AgendaHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AgendaHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // endregion

    // region Public methods

    public void setDay(Calendar day, int currentDayTextColor) {
        TextView txtDayOfMonth = (TextView) findViewById(R.id.view_agenda_day_of_month);
        TextView txtDayOfWeek = (TextView) findViewById(R.id.view_agenda_day_of_week);
        View circleView = findViewById(R.id.view_day_circle_selected);

        Calendar today = CalendarManager.getInstance().getToday();

        SimpleDateFormat dayWeekFormatter = new SimpleDateFormat("E");

        txtDayOfMonth.setTextColor(getResources().getColor(R.color.calendar_text_default));
        txtDayOfWeek.setTextColor(getResources().getColor(R.color.calendar_text_default));

        if (DateHelper.sameDate(day, today)) {
            //cor do texto no dia do mes atual
            txtDayOfMonth.setTextColor(getResources().getColor(R.color.corDoTextoDiasAtivos));
            circleView.setVisibility(VISIBLE);
            GradientDrawable drawable = (GradientDrawable) circleView.getBackground();
            //alterando cor da borda dia atual na agenda view
            drawable.setStroke((int) (1 * Resources.getSystem().getDisplayMetrics().density), getResources().getColor(R.color.corBordaSelecao));
        } else {
            circleView.setVisibility(INVISIBLE);
        }

        txtDayOfMonth.setText(String.valueOf(day.get(Calendar.DAY_OF_MONTH)));
        txtDayOfWeek.setText(dayWeekFormatter.format(day.getTime()));
    }

    // endregion
}
