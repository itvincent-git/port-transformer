package net.port.transformer.sample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        send_report.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                ReportHelper.getInstance(this@MainActivity)
                    .sampleReport.report("click", "123")

                ReportHelper.getInstance(this@MainActivity)
                    .sampleReport2.report2_2("click", "123", 123, 4566666666666666666L)

                ReportHelper.getInstance(this@MainActivity)
                    .sampleReport2.reportObject("click", "report_object", 100, 28888377777888L)
            }
        })
    }
}
