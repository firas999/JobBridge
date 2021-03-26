<?php

namespace App\Form;

use App\Entity\OffreStage;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use App\Entity\Entreprise;
use Symfony\Component\Form\Extension\Core\Type\CollectionType;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
class OffreStageType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
                ->add('TypeStage', ChoiceType::class, array(
                'choices' => array(
                'Stage ouvrier' => 'Ouvrier',
                'Stage technicien' => 'Technicien',
                'Stage PFE' => 'PFE',
                ),
                ))
            ->add('Duree')
            ->add('Exigence')
            ->add('IdEntreprise', EntityType::class, [
                'class'=>Entreprise::class,
                'choice_label'=>'nom',
                
                ])


                ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => OffreStage::class,
        ]);
    }
}
