package com.teamvinay.newphotoediter.editor.filterscolor;

import android.graphics.Bitmap;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import org.wysaid.common.SharedContext;
import org.wysaid.nativePort.CGEImageHandler;

public class FilterUtils {
    public static final FilterBean[] EFFECT_CONFIGS = {new FilterBean("", "Original"), new FilterBean("@adjust lut filters/bright01.webp", "Fresh 01"), new FilterBean("@adjust lut filters/bright02.webp", "Fresh 02"), new FilterBean("@adjust lut filters/bright03.webp", "Fresh 03"), new FilterBean("@adjust lut filters/bright05.webp", "Fresh 05"), new FilterBean("@adjust lut filters/portrait_film.png", "Portrait Film"), new FilterBean("@adjust lut filters/cali_vibes.png", "Cali Vibes"), new FilterBean("@adjust lut filters/arctic_chill.png", "Arctic Chill"), new FilterBean("@adjust lut filters/vibrant_light.png", "Vibrant Light"), new FilterBean("@adjust lut filters/binary_code.png", "Binary Code"), new FilterBean("@adjust lut filters/soft_fade.png", "Soft Fade"), new FilterBean("@adjust lut filters/lush_land.png", "Lush Land"), new FilterBean("@adjust lut filters/hdr_color.png", "HDR Color"), new FilterBean("@adjust lut filters/warm_teal.png", "Warm Teal"), new FilterBean("@adjust lut filters/hollywood.png", "Hollywood"), new FilterBean("@adjust lut filters/kodak_color.png", "Kodak Color"), new FilterBean("@adjust lut filters/euro01.webp", "Euro 01"), new FilterBean("@adjust lut filters/euro02.webp", "Euro 02"), new FilterBean("@adjust lut filters/euro05.webp", "Euro 03"), new FilterBean("@adjust lut filters/euro04.webp", "Euro 04"), new FilterBean("@adjust lut filters/euro06.webp", "Euro 05"), new FilterBean("@adjust lut filters/film01.webp", "Film 01"), new FilterBean("@adjust lut filters/film02.webp", "Film 02"), new FilterBean("@adjust lut filters/film03.webp", "Film 03"), new FilterBean("@adjust lut filters/film04.webp", "Film 04"), new FilterBean("@adjust lut filters/film05.webp", "Film 05"), new FilterBean("@adjust lut filters/lomo1.webp", "Lomo 01"), new FilterBean("@adjust lut filters/lomo2.webp", "Lomo 02"), new FilterBean("@adjust lut filters/lomo3.webp", "Lomo 03"), new FilterBean("@adjust lut filters/lomo4.webp", "Lomo 04"), new FilterBean("@adjust lut filters/lomo5.webp", "Lomo 05"), new FilterBean("@adjust lut filters/movie01.webp", "Movie 01"), new FilterBean("@adjust lut filters/movie02.webp", "Movie 02"), new FilterBean("@adjust lut filters/movie03.webp", "Movie 03"), new FilterBean("@adjust lut filters/movie04.webp", "Movie 04"), new FilterBean("@adjust lut filters/movie05.webp", "Movie 05"), new FilterBean("@adjust lut filters/cube1.jpg", "Cube 1"), new FilterBean("@adjust lut filters/cube2.jpg", "Cube 2"), new FilterBean("@adjust lut filters/cube3.jpg", "Cube 3"), new FilterBean("@adjust lut filters/cube4.jpg", "Cube 4"), new FilterBean("@adjust lut filters/cube5.jpg", "Cube 5"), new FilterBean("@adjust lut filters/cube6.jpg", "Cube 6"), new FilterBean("@adjust lut filters/cube7.jpg", "Cube 7"), new FilterBean("@adjust lut filters/cube8.jpg", "Cube 8"), new FilterBean("@adjust lut filters/cube9.jpg", "Cube 9"), new FilterBean("@adjust lut filters/cube10.jpg", "Cube 10")};
    public static final FilterBean[] OVERLAY_CONFIG = {new FilterBean("", ""), new FilterBean("#unpack @krblend sr overlay/01.jpg 100", ""), new FilterBean("#unpack @krblend sr overlay/2.jpg 100", ""), new FilterBean("#unpack @krblend sr overlay/02.jpg 100", ""), new FilterBean("#unpack @krblend sr overlay/3.jpg 100", ""), new FilterBean("#unpack @krblend sr overlay/4.jpg 100", ""), new FilterBean("#unpack @krblend sr overlay/5.png 100", ""), new FilterBean("#unpack @krblend sr overlay/1.jpg 100", ""), new FilterBean("#unpack @krblend sr overlay/19.jpg 100", ""), new FilterBean("#unpack @krblend sr overlay/46.jpg 100", ""), new FilterBean("#unpack @krblend sr overlay/47.jpg 100", ""), new FilterBean("#unpack @krblend sr overlay/effect_00005.jpg 100", ""), new FilterBean("#unpack @krblend sr overlay/26.jpg 100", ""), new FilterBean("#unpack @krblend sr overlay/35.jpg 100", ""), new FilterBean("#unpack @krblend sr overlay/42.jpg 100", ""), new FilterBean("#unpack @krblend sr overlay/43.jpg 100", ""), new FilterBean("#unpack @krblend sr overlay/44.jpg 100", ""), new FilterBean("#unpack @krblend sr overlay/45.jpg 100", ""), new FilterBean("#unpack @krblend sr overlay/effect_00018.jpg 100", ""), new FilterBean("#unpack @krblend sr overlay/effect_00025.jpg 100", ""), new FilterBean("#unpack @krblend sr overlay/effect_00026.jpg 100", ""), new FilterBean("#unpack @krblend sr overlay/effect_00031.jpg 100", ""), new FilterBean("#unpack @krblend sr overlay/effect_00037.jpg 100", ""), new FilterBean("#unpack @krblend sr overlay/53.jpg 100", ""), new FilterBean("#unpack @krblend sr overlay/54.jpg 100", ""), new FilterBean("#unpack @krblend sr overlay/55.jpg 100", ""), new FilterBean("#unpack @krblend sr overlay/56.jpg 100", ""), new FilterBean("#unpack @krblend sr overlay/57.jpg 100", ""), new FilterBean("#unpack @krblend sr overlay/11.png 100", ""), new FilterBean("#unpack @krblend sr overlay/12.png 100", ""), new FilterBean("#unpack @krblend sr overlay/13.png 100", "")};

    public static class FilterBean {
        private String config;
        private String name;

        FilterBean(String str, String str2) {
            this.config = str;
            this.name = str2;
        }

        public String getConfig() {
            return this.config;
        }

        public void setConfig(String str) {
            this.config = str;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }
    }

    public static Bitmap getBlurImageFromBitmap(Bitmap bitmap) {
        SharedContext create = SharedContext.create();
        create.makeCurrent();
        CGEImageHandler cGEImageHandler = new CGEImageHandler();
        cGEImageHandler.initWithBitmap(bitmap);
        cGEImageHandler.setFilterWithConfig("@blur lerp 0.6");
        cGEImageHandler.processFilters();
        Bitmap resultBitmap = cGEImageHandler.getResultBitmap();
        create.release();
        return resultBitmap;
    }

    public static Bitmap getBlurImageFromBitmap(Bitmap bitmap, float f) {
        SharedContext create = SharedContext.create();
        create.makeCurrent();
        CGEImageHandler cGEImageHandler = new CGEImageHandler();
        cGEImageHandler.initWithBitmap(bitmap);
        cGEImageHandler.setFilterWithConfig(MessageFormat.format("@blur lerp {0}", new Object[]{(f / 10.0f) + ""}));
        cGEImageHandler.processFilters();
        Bitmap resultBitmap = cGEImageHandler.getResultBitmap();
        create.release();
        return resultBitmap;
    }

    public static Bitmap cloneBitmap(Bitmap bitmap) {
        SharedContext create = SharedContext.create();
        create.makeCurrent();
        CGEImageHandler cGEImageHandler = new CGEImageHandler();
        cGEImageHandler.initWithBitmap(bitmap);
        cGEImageHandler.setFilterWithConfig("");
        cGEImageHandler.processFilters();
        Bitmap resultBitmap = cGEImageHandler.getResultBitmap();
        create.release();
        return resultBitmap;
    }

    public static Bitmap getBlackAndWhiteImageFromBitmap(Bitmap bitmap) {
        SharedContext create = SharedContext.create();
        create.makeCurrent();
        CGEImageHandler cGEImageHandler = new CGEImageHandler();
        cGEImageHandler.initWithBitmap(bitmap);
        cGEImageHandler.setFilterWithConfig("@adjust saturation 0");
        cGEImageHandler.processFilters();
        Bitmap resultBitmap = cGEImageHandler.getResultBitmap();
        create.release();
        return resultBitmap;
    }

    public static Bitmap getBitmapWithFilter(Bitmap bitmap, String str) {
        SharedContext create = SharedContext.create();
        create.makeCurrent();
        CGEImageHandler cGEImageHandler = new CGEImageHandler();
        cGEImageHandler.initWithBitmap(bitmap);
        cGEImageHandler.setFilterWithConfig(str);
        cGEImageHandler.setFilterIntensity(0.8f);
        cGEImageHandler.processFilters();
        Bitmap resultBitmap = cGEImageHandler.getResultBitmap();
        create.release();
        return resultBitmap;
    }

    public static List<Bitmap> getLstBitmapWithFilter(Bitmap bitmap) {
        ArrayList arrayList = new ArrayList();
        SharedContext create = SharedContext.create();
        create.makeCurrent();
        CGEImageHandler cGEImageHandler = new CGEImageHandler();
        cGEImageHandler.initWithBitmap(bitmap);
        for (FilterBean config : EFFECT_CONFIGS) {
            cGEImageHandler.setFilterWithConfig(config.getConfig());
            cGEImageHandler.processFilters();
            arrayList.add(cGEImageHandler.getResultBitmap());
        }
        create.release();
        return arrayList;
    }

    public static List<Bitmap> getLstBitmapWithOverlay(Bitmap bitmap) {
        ArrayList arrayList = new ArrayList();
        SharedContext create = SharedContext.create();
        create.makeCurrent();
        CGEImageHandler cGEImageHandler = new CGEImageHandler();
        cGEImageHandler.initWithBitmap(bitmap);
        for (FilterBean config : OVERLAY_CONFIG) {
            cGEImageHandler.setFilterWithConfig(config.getConfig());
            cGEImageHandler.processFilters();
            arrayList.add(cGEImageHandler.getResultBitmap());
        }
        create.release();
        return arrayList;
    }
}