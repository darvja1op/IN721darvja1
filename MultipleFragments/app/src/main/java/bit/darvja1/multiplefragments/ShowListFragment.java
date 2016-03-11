package bit.darvja1.multiplefragments;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ShowListFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_image, container, false);
        ShowListFragment fragment = new ShowListFragment();

        ListView lvCities = (ListView) fragmentView.findViewById(R.id.listView);

        Resources resourceMachine = getResources();
        String[] cityNamesArray = resourceMachine.getStringArray(R.array.cities);

        ArrayAdapter<String> cityNamesAdapter = new ArrayAdapter<String>(getActivity(),
                                                                            android.R.layout.simple_list_item_1,cityNamesArray);
        lvCities.setAdapter(cityNamesAdapter);

        return fragmentView;
    }
}
