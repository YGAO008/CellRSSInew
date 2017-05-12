package com.example.gaojael.cellrssi;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.CellInfo;
import android.telephony.CellInfoWcdma;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/*
 * TelephoneManager 获取 lac:mcc:mnc:cell_id
 * # MCC,mobile country code 移动国家代码
 * # MNC，mobile network code，移动网络号码(中国移动为0，中国联通为1， 中国电信为2)
 * # LAC，Location Area Code, 位置区域码
 * # CID, Cell Identity, 基站编号
 * # BSSS, Base station signal strength，基站信号强度
 */

public class MainActivity extends Activity {

    Button bn;
    TextView tx;

    private static final  String TAG = "GSMCellLocationActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tx = (TextView)findViewById(R.id.tx);

        // 获取基站信息
        findViewById(R.id.bn).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                TelephonyManager mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

//                // 返回值MCC + MNC
//                String operator = mTelephonyManager.getNetworkOperator();
//                int mcc = Integer.parseInt(operator.substring(0, 3));
//                int mnc = Integer.parseInt(operator.substring(3));
//
//
//                // 中国移动和中国联通获取LAC、CID的方式
//                GsmCellLocation location = (GsmCellLocation) mTelephonyManager.getCellLocation();
//                int lac = location.getLac();
//                int cellId = location.getCid();
//
//
//                Log.i(TAG, " MCC = " + mcc + "\t MNC = " + mnc + "\t LAC = " + lac + "\t CID = " + cellId);
//
//
//                tx.setText(" MCC = " + mcc + "\t MNC = " + mnc + "\t LAC = " + lac + "\t CID = " + cellId);
//
//                // 中国电信获取LAC、CID的方式
//                /*CdmaCellLocation location1 = (CdmaCellLocation) mTelephonyManager.getCellLocation();
//                lac = location1.getNetworkId();
//                cellId = location1.getBaseStationId();
//                cellId /= 16;*/
//
//                // 获取邻区基站信息
//                List<NeighboringCellInfo> infos = mTelephonyManager.getNeighboringCellInfo();
//                StringBuffer sb = new StringBuffer("总数 : " + infos.size() + "\n");
//                sb.append(" MCC = " + mcc + "\t MNC = " + mnc + "\t LAC = " + lac + "\t CID = " + cellId);
//                for (NeighboringCellInfo info1 : infos) { // 根据邻区总数进行循环
//                    sb.append(" LAC : " + info1.getLac()); // 取出当前邻区的LAC
//                   sb.append(" CID : " + info1.getCid()); // 取出当前邻区的CID
//                   sb.append(" BSSS : " + (-113 + 2 * info1.getRssi()) + "\n"); // 获取邻区基站信号强度
//                }
//
//                Log.i(TAG, " 获取邻区基站信息:" + sb.toString());
//
//                tx.setText(" 获取邻区基站信息:" + sb.toString());



                /*=====================================================================================*/

                StringBuffer sb = new StringBuffer("");

                List<CellInfo> tc= mTelephonyManager.getAllCellInfo();

                for(int i=0;i<tc.size();i++) {

                    CellInfoWcdma cdk = (CellInfoWcdma) tc.get(i);

                    int cid1 = cdk.getCellIdentity().getCid();

                    int mcc1 = cdk.getCellIdentity().getMcc();

                    int mnc1 = cdk.getCellIdentity().getMnc();

                    //int fcn = cdk.getCellIdentity().getUarfcn();

                    int cd = cdk.getCellSignalStrength().getDbm();

                    sb.append("Cid:"+cid1+"\t Mcc:"+mcc1+"\t Mnc:"+mnc1+"\t Rss:" + cd + "\n");
                }

                tx.setText("rss = "+sb.toString());
            }
        });
    }
}
