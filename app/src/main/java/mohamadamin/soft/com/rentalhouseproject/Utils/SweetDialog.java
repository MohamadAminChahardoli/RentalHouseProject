package mohamadamin.soft.com.rentalhouseproject.Utils;

import android.content.Context;
import cn.pedant.SweetAlert.SweetAlertDialog;
import mohamadamin.soft.com.rentalhouseproject.R;

public class SweetDialog {

    private SweetAlertDialog SweetDialog;

    private SweetDialog() {}

    public void show()
    {
        SweetDialog.show();
    }

    public void hide()
    {
        SweetDialog.dismiss();
    }

    public SweetDialog setContentText(String contentText)
    {
        SweetDialog.setContentText(contentText);
        return this;
    }

    public static class Builder
    {
        private String TitleText;
        private String ContentText;
        private int DialogType;
        private SweetDialog SweetDialogInstance;


        public Builder setDialogType(int dialogType)
        {
            DialogType = dialogType;
            return this;
        }

        public Builder setTitleText(String titleText)
        {
            TitleText = titleText;
            return this;
        }

        public SweetDialog build(Context context)
        {
            SweetDialogInstance = new SweetDialog();
            SweetDialogInstance.SweetDialog = new SweetAlertDialog(context, DialogType);
            SweetDialogInstance.SweetDialog.setTitleText(TitleText);
            SweetDialogInstance.SweetDialog.setContentText(ContentText);
            SweetDialogInstance.SweetDialog.getProgressHelper().setBarColor(context.getResources().getColor(R.color.colorPrimaryDark));
            SweetDialogInstance.SweetDialog.setCancelable(false);
            SweetDialogInstance.SweetDialog.setConfirmButton("باشه", new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    SweetDialogInstance.SweetDialog.dismiss();
                }
            });

            return SweetDialogInstance;
        }

    }

}
