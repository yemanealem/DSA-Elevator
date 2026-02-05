     int l = 0, r = height.length - 1, max = 0;

        while (l < r) {
            int h;
            if (height[l] < height[r]) {
                h = height[l];
                int area = h * (r - l);
                if (area > max) max = area;
                l++;
            } else {
                h = height[r];
                int area = h * (r - l);
                if (area > max) max = area;
                r--;
            }
        }
        return max;