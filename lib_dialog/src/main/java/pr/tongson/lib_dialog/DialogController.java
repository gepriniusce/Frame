//package pr.tongson.lib_dialog;
//
//import android.view.Gravity;
//import android.view.View;
//
///**
// * <b>Create Date:</b> 2020-01-30<br>
// * <b>Email:</b> 289286298@qq.com<br>
// * <b>Description:</b>  <br>
// *
// * @author tongson
// */
//public class DialogController {
//    private int layoutRes, animRes;
//    private int dialogWidth, dialogHeight;
//    private float dimAmount = 0.2f;
//    private int gravity = Gravity.CENTER;
//    private boolean isCanTouchOutside = true, cancelable = false;
//    private View dialogView;
//
//
//    public int getLayoutRes() {
//        return layoutRes;
//    }
//
//    public int getAnimRes() {
//        return animRes;
//    }
//
//    public int getDialogWidth() {
//        return dialogWidth;
//    }
//
//    public int getDialogHeight() {
//        return dialogHeight;
//    }
//
//    public float getDimAmount() {
//        return dimAmount;
//    }
//
//    public int getGravity() {
//        return gravity;
//    }
//
//    public boolean isCanTouchOutside() {
//        return isCanTouchOutside;
//    }
//
//    public boolean isCancelable() {
//        return cancelable;
//    }
//
//    public View getDialogView() {
//        return dialogView;
//    }
//
//
//    public void setLayoutRes(int layoutRes) {
//        this.layoutRes = layoutRes;
//    }
//
//    public void setAnimRes(int animRes) {
//        this.animRes = animRes;
//    }
//
//    public void setDialogWidth(int dialogWidth) {
//        this.dialogWidth = dialogWidth;
//    }
//
//    public void setDialogHeight(int dialogHeight) {
//        this.dialogHeight = dialogHeight;
//    }
//
//    public void setDimAmount(float dimAmount) {
//        this.dimAmount = dimAmount;
//    }
//
//    public void setGravity(int gravity) {
//        this.gravity = gravity;
//    }
//
//    public void setCanTouchOutside(boolean canTouchOutside) {
//        isCanTouchOutside = canTouchOutside;
//    }
//
//    public void setCancelable(boolean cancelable) {
//        this.cancelable = cancelable;
//    }
//
//    public void setDialogView(View dialogView) {
//        this.dialogView = dialogView;
//    }
//
//    public static class Params {
//        private int layoutRes, animRes;
//        private int dialogWidth, dialogHeight;
//        private float dimAmount = 0.2f;
//        private int gravity = Gravity.CENTER;
//        private boolean isCanTouchOutside = true;
//        private boolean cancelable = false;
//        private View dialogView;
//
//        public int getLayoutRes() {
//            return layoutRes;
//        }
//
//        public int getAnimRes() {
//            return animRes;
//        }
//
//        public int getDialogWidth() {
//            return dialogWidth;
//        }
//
//        public int getDialogHeight() {
//            return dialogHeight;
//        }
//
//        public float getDimAmount() {
//            return dimAmount;
//        }
//
//        public int getGravity() {
//            return gravity;
//        }
//
//        public boolean isCanTouchOutside() {
//            return isCanTouchOutside;
//        }
//
//        public boolean isCancelable() {
//            return cancelable;
//        }
//
//        public View getDialogView() {
//            return dialogView;
//        }
//
//        public void setLayoutRes(int layoutRes) {
//            this.layoutRes = layoutRes;
//        }
//
//        public void setAnimRes(int animRes) {
//            this.animRes = animRes;
//        }
//
//        public void setDialogWidth(int dialogWidth) {
//            this.dialogWidth = dialogWidth;
//        }
//
//        public void setDialogHeight(int dialogHeight) {
//            this.dialogHeight = dialogHeight;
//        }
//
//        public void setDimAmount(float dimAmount) {
//            this.dimAmount = dimAmount;
//        }
//
//        public void setGravity(int gravity) {
//            this.gravity = gravity;
//        }
//
//        public void setCanTouchOutside(boolean canTouchOutside) {
//            isCanTouchOutside = canTouchOutside;
//        }
//
//        public void setCancelable(boolean cancelable) {
//            this.cancelable = cancelable;
//        }
//
//        public void setDialogView(View dialogView) {
//            this.dialogView = dialogView;
//        }
//
//        void apply(DialogController dialogController) {
//            dialogController.setAnimRes(animRes);
//            dialogController.setDimAmount(dimAmount);
//            dialogController.setGravity(gravity);
//            dialogController.setCanTouchOutside(isCanTouchOutside);
//            dialogController.setCancelable(cancelable);
//            if (layoutRes > 0) {
//                dialogController.setLayoutRes(layoutRes);
//            } else if (dialogView != null) {
//                dialogController.setDialogView(dialogView);
//            } else {
//                throw new IllegalArgumentException("null");
//            }
//            if (dialogWidth > 0) {
//                dialogController.setDialogWidth(dialogWidth);
//
//            }
//            if (dialogHeight > 0) {
//                dialogController.setDialogHeight(dialogHeight);
//            }
//        }
//    }
//
//}
